package com.java_simple.codes.reflect;


import com.java_simple.codes.PKt;

import java.lang.reflect.*;

public class UserReflect {


    public static void main(String[] args) {

        //反射获取 对象实例
//        reflectGetInstance();

        //反射获取 构造器
//        reflectConstructor();

        // 反射获取 私有属性
//        reflectPrivateField();

        //反射调用 私有方法
//        reflectPrivateMethod();


        //测试反射 类中的API
//        testReflectClassAPI();

        testReflectMethod();
    }

    private static void testReflectMethod() {

        try {
            Class<?> clazz = Class.forName("com.java_simple.codes.reflect.User");
            User user = new User();

            Method[] methods = clazz.getDeclaredMethods();
            for(Method md:methods){
                PKt.out(md.toString()+", isSynthetic = "+md.isSynthetic());
            }

            PKt.out(" getTypeName() =  "+clazz.getTypeName());
            PKt.out(" getTypeName() =  "+clazz.getTypeParameters());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void testReflectClassAPI() {

        // 如果 无参构造器使用 private 修饰 调用 newInstance() 并不能创建其对象实例
        try {
            Class<?> clazz = Class.forName("com.java_simple.codes.reflect.User");

            // 获取所有 公开类或接口 (public修饰的)
            Class<?>[] classes = clazz.getClasses();
            for (Class<?> c : classes){
                PKt.out("类名 ： "+c.getSimpleName());
            }
            // 判断传入的对象 是否是 此class 的实例对象
            User user = new User();
            boolean isInstance = clazz.isInstance(user);
            PKt.out("inStance = "+isInstance);

            // --测试-isAssignableFrom()-----------
            User user1 = new User();
            Person person = new Person();
            Person absPerson = new User();

            PKt.out("--------------begin---------------");

            PKt.out(User.class.isAssignableFrom(user1.getClass()));
            PKt.out(Person.class.isAssignableFrom(person.getClass()));

            PKt.out(Person.class.isAssignableFrom(user1.getClass()));
            PKt.out(User.class.isAssignableFrom(person.getClass()));

            PKt.out(Person.class.isAssignableFrom(absPerson.getClass()));
            PKt.out(User.class.isAssignableFrom(absPerson.getClass()));

            PKt.out("--------------1---------------");

            PKt.out(user1.getClass().isAssignableFrom(User.class));
            PKt.out(user1.getClass().isAssignableFrom(Person.class));

            PKt.out(person.getClass().isAssignableFrom(User.class));
            PKt.out(person.getClass().isAssignableFrom(Person.class));

            PKt.out(absPerson.getClass().isAssignableFrom(User.class));
            PKt.out(absPerson.getClass().isAssignableFrom(Person.class));

            PKt.out("--------------2---------------");

            PKt.out(Object.class.isAssignableFrom(user.getClass()));
            PKt.out(Object.class.isAssignableFrom(person.getClass()));
            PKt.out(Object.class.isAssignableFrom("a".getClass()));

            PKt.out("abc".getClass().isAssignableFrom(Object.class));

            PKt.out("-------------end--------------");

            PKt.out(User.class.isInterface());
            PKt.out(User.IUserData.class.isInterface());

            PKt.out("-------------others-------------");
            PKt.out(User.class.isArray());

            PKt.out(User.class.isPrimitive());

            PKt.out("-----------annotation--------------");
            // annotation
            PKt.out(User.UserType.class.isAnnotation());
            PKt.out(User.class.isAnnotation());



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    private static void reflectPrivateMethod() {
        try {
            // 如果 无参构造器使用 private 修饰 调用 newInstance() 并不能创建其对象实例
            Class<?> clazz = Class.forName("com.java_simple.codes.reflect.User");

            User user = (User) clazz.newInstance();

            Method method = clazz.getDeclaredMethod("isVipUser", int.class);
            method.setAccessible(true);

            PKt.out("修饰符 ： "+Modifier.toString(method.getModifiers()));

            // 使用 invoke 调用指定方法
            boolean re = (boolean) method.invoke(user,0);
            PKt.out("re = "+re);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static void reflectPrivateField() {
        try {
            // 如果 无参构造器使用 private 修饰 调用 newInstance() 并不能创建其对象实例
            Class<?> clazz = Class.forName("com.java_simple.codes.reflect.User");
            User user = (User) clazz.newInstance();

            Field fieldTag = clazz.getDeclaredField("TAG");

            fieldTag.setAccessible(true);
            String tagValue = (String) fieldTag.get(user);

            PKt.out("tagValue = "+tagValue);

            Field fieldUserId = clazz.getDeclaredField("userId");
            Field fieldUserName = clazz.getDeclaredField("userName");

            // 反射调用 其私有属性 必须先进行解除封装
            fieldUserId.setAccessible(true);
            fieldUserName.setAccessible(true);

            fieldUserId.setInt(user,9);
            fieldUserName.set(user,"C++");

            PKt.out("user = "+user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    private static void reflectGetInstance(){
        try {
            // 如果 无参构造器使用 private 修饰 调用 newInstance() 并不能创建其对象实例
            Class<?> clazz = Class.forName("com.java_simple.codes.reflect.User");
            User user = (User) clazz.newInstance();
            user.setUserId(1);
            user.setUserName("Java");

            PKt.out(user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static void reflectConstructor(){
        try {
            Class<?> clazz = Class.forName("com.java_simple.codes.reflect.User");
            Constructor<User> constructor = (Constructor<User>) clazz.getDeclaredConstructor(int.class,String.class);
            int modifiers = constructor.getModifiers();
            PKt.out("修饰符 : modifiers = "+ Modifier.toString(modifiers));
            PKt.out("name : "+constructor.getName());
            // 解除封装
            constructor.setAccessible(true);

            User user = constructor.newInstance(0,"Android");

            PKt.out("user = "+user);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }



}
