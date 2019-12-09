package usage.ywb.personal.evolvement.base.utils;

import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.reflect.ReflectionAccessor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Kingdee.ywb
 * @version [ V.2.7.1  2019/12/6 ]
 */
public class ReflectMethodAdapterFactory implements TypeAdapterFactory {

    private final ConstructorConstructor constructorConstructor;
    private final ReflectionAccessor accessor = ReflectionAccessor.getInstance();

    public ReflectMethodAdapterFactory() {
        this.constructorConstructor = new ConstructorConstructor(Collections.<Type, InstanceCreator<?>>emptyMap());
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {
        Class<? super T> raw = type.getRawType();
        if (!Object.class.isAssignableFrom(raw)) {
            return null; // it's a primitive!
        }
        Map<String, BoundMethod> map = getBoundMethods(gson, type, raw);
        if (map.size() == 0) {
            return null;
        }
        ObjectConstructor<T> constructor = constructorConstructor.get(type);
        return new Adapter<>(constructor, map);
    }

    private Map<String, BoundMethod> getBoundMethods(Gson context, TypeToken<?> type, Class<?> raw) {
        Map<String, BoundMethod> result = new LinkedHashMap<String, BoundMethod>();
        if (raw.isInterface()) {
            return result;
        }
        Type declaredType = type.getType();
        while (raw != Object.class) {
            Method[] methods = raw.getDeclaredMethods();
            for (Method method : methods) {
                List<String> methodNames = getMethodNames(method);
                if (methodNames == null) {
                    continue;
                }
                accessor.makeAccessible(method);
                Class parameterType = method.getParameterTypes()[0];
                Type fieldType = $Gson$Types.resolve(type.getType(), raw, parameterType);
                BoundMethod previous = null;
                for (int i = 0, size = methodNames.size(); i < size; ++i) {
                    String name = methodNames.get(i);
                    BoundMethod boundMethod = createBoundMethod(context, method, name, TypeToken.get(fieldType));
                    BoundMethod replaced = result.put(name, boundMethod);
                    if (previous == null) previous = replaced;
                }
                if (previous != null) {
                    throw new IllegalArgumentException(declaredType
                            + " declares multiple JSON fields named " + previous.name);
                }
            }
            type = TypeToken.get(Objects.requireNonNull($Gson$Types.resolve(type.getType(), raw, raw.getGenericSuperclass())));
            raw = type.getRawType();
        }
        return result;
    }

    private List<String> getMethodNames(Method method) {
        SerializedName annotation = method.getAnnotation(SerializedName.class);
        if (annotation != null) {
            Class[] parameterizedType = method.getParameterTypes();
            if (parameterizedType.length == 1) {
                String serializedName = annotation.value();
                String[] alternates = annotation.alternate();
                if (alternates.length == 0) {
                    return Collections.singletonList(serializedName);
                }
                List<String> methodNames = new ArrayList<>(alternates.length + 1);
                methodNames.add(serializedName);
                methodNames.addAll(Arrays.asList(alternates));
                return methodNames;
            }
        }
        return null;
    }

    private BoundMethod createBoundMethod(final Gson context, final Method method, final String name, final TypeToken<?> fieldType) {
        final boolean isPrimitive = Primitives.isPrimitive(fieldType.getRawType());
        // special casing primitives here saves ~5% on Android...
        final TypeAdapter<?> typeAdapter = context.getAdapter(fieldType);
        return new BoundMethod(name) {
            @Override
            void read(JsonReader reader, Object value)
                    throws IOException, IllegalAccessException {
                Object fieldValue = typeAdapter.read(reader);
                if (fieldValue != null || !isPrimitive) {
                    try {
                        method.invoke(value, fieldValue);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }


    abstract class BoundMethod {
        final String name;

        BoundMethod(String name) {
            this.name = name;
        }

        abstract void read(JsonReader reader, Object value) throws IOException, IllegalAccessException;
    }

    public static final class Adapter<T> extends TypeAdapter<T> {
        private final ObjectConstructor<T> constructor;
        private final Map<String, BoundMethod> boundFields;

        Adapter(ObjectConstructor<T> constructor, Map<String, BoundMethod> boundFields) {
            this.constructor = constructor;
            this.boundFields = boundFields;
        }

        @Override
        public T read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }

            //TODO
            /**
             * {@link com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter#read(JsonReader)}中也有
             * <code>
             *     T instance = constructor.construct();
             * </code>
             * 此处如果也创建，那么对于Method和Field的解析，不再是同一个对象，还没想到解决方案
             */
            T instance = constructor.construct();

            try {
                in.beginObject();
                while (in.hasNext()) {
                    String name = in.nextName();
                    BoundMethod method = boundFields.get(name);
                    if (method == null) {
                        in.skipValue();
                    } else {
                        method.read(in, instance);
                    }
                }
            } catch (IllegalStateException e) {
                throw new JsonSyntaxException(e);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
            in.endObject();
            return instance;
        }

        @Override
        public void write(JsonWriter out, T value) {

        }
    }

}
