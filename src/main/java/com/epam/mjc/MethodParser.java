package com.epam.mjc;


import java.util.ArrayList;
import java.util.List;


public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String accessModifier;
        String returnType;
        String methodName;
        MethodSignature ms;

        String[] res = signatureString.substring(0, signatureString.indexOf("(")).split(" ");


        if (res.length == 3) {

            accessModifier = res[0];
            returnType = res[1];
            methodName = res[2];


        } else {
            accessModifier = null;
            returnType = res[0];
            methodName = res[1];

        }


        ms = new MethodSignature(methodName, createArgsList(signatureString));
        ms.setAccessModifier(accessModifier);
        ms.setReturnType(returnType);
        return ms;
    }

    private List<MethodSignature.Argument> createArgsList(String arguments) {
        List<MethodSignature.Argument> res = new ArrayList<>();
        String checkArg = arguments.substring(arguments.indexOf("("), arguments.indexOf(")"))
                .replaceAll("\\(", "")
                .replaceAll("\\)", "");
        if (checkArg.trim().isEmpty()) {
            return res;
        } else {
            String[] argumentsArr;
            argumentsArr = arguments.substring(arguments
                            .indexOf("(") + 1)
                    .replace("(", "")
                    .replace(")", "")
                    .split(",");
            for (String arg : argumentsArr) {
                String[] resArg = arg.trim().split(" ");
                res.add(new MethodSignature.Argument(resArg[0], resArg[1]));
            }

        }
        return res;
    }
}
