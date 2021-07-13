package top.yms.recent.code7;

import java.lang.reflect.*;

public class Code712 {

    /**
     * prints all constructors of a class
     * @param cl a class
     */
    public static void printConstructors(Class cl)
    {
        Constructor[] constructors=cl.getDeclaredConstructors();

        for(Constructor c:constructors){
            String name=c.getName();
            System.out.println(" ");
            String modifiers=Modifier.toString(c.getModifiers());
            if(modifiers.length()>0)System.out.print(modifiers+" ");
            System.out.print(name+"(");

//print parameter types
            Class[] paramTypes=c.getParameterTypes();
            for(int j=0;j<paramTypes.length;j++){
                if(j>0)System.out.print(" ,");
                System.out.print(paramTypes[j].getName());
            }
            System.out.print(");");
        }
    }

    /**
     * print all methods of a class
     * param cl is a class
     */
    public static void printMethods(Class cl){
        Method[] methods=cl.getDeclaredMethods();

        for(Method m:methods){
            Class retType=m.getReturnType();
            String name=m.getName();

            System.out.println(" ");
//print modifiers ,return type,and method name
            String modifiers=Modifier.toString(m.getModifiers());
            if(modifiers.length()>0){
                System.out.print(modifiers+" "+retType.getName()+" "+name+"(");
//print parameter types
                Class[] paramTypes=m.getParameterTypes();
                for(int j=0;j<paramTypes.length;j++){
                    if (j>0) {
                        System.out.print(",");
                    }
                    System.out.print(paramTypes[j].getName());
                }
                System.out.println(");");
            }
        }
    }
    /**
     * print all fields of a class
     * @param cl
     */
    public static void printFields(Class cl){
        Field[] fields=cl.getDeclaredFields();

        for(Field f:fields){
            Class type=f.getType();
            String name=f.getName();
            System.out.print(" ");
            String modifiers=Modifier.toString(f.getModifiers());
            if(modifiers.length()>0)System.out.print(modifiers+" ");
            System.out.println(type.getName()+" "+name+" ;");
        }
// System.out.println(" printFields()is ends!");
    }


}
