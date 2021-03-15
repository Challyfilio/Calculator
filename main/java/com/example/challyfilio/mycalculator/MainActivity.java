package com.example.challyfilio.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //数字
    private Button num0;
    private Button num1;
    private Button num2;
    private Button num3;
    private Button num4;
    private Button num5;
    private Button num6;
    private Button num7;
    private Button num8;
    private Button num9;
    //运算符
    private Button add;
    private Button sub;
    private Button multiply;
    private Button divide;
    private Button bt_sqrt;
    private Button equal;
    //其他
    private Button clear;
    private Button delete;
    private Button change;
    private Button point;
    //瞎扯
    private TextView inputText;//输入
    private TextView outputText;//输出 ////////////////////////////////////////////////////////////201804241246

    private String existsText = "";//已输入字符
    private String param1 = "", param2 = ""; //用于记录加减乘除运算符前/后输入内容
    private String tempResult;//用于存储运算结果的字符串

    private double arg1, arg2;//转换后的两个参数
    private double result;//用于存储运算结果
    //判断
    private boolean flag_counted = false;//用于判断是否计算

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* * * * * 初始化控件 * * * * */
        //数字
        num0 = (Button) findViewById(R.id.num0_btn);
        num1 = (Button) findViewById(R.id.num1_btn);
        num2 = (Button) findViewById(R.id.num2_btn);
        num3 = (Button) findViewById(R.id.num3_btn);
        num4 = (Button) findViewById(R.id.num4_btn);
        num5 = (Button) findViewById(R.id.num5_btn);
        num6 = (Button) findViewById(R.id.num6_btn);
        num7 = (Button) findViewById(R.id.num7_btn);
        num8 = (Button) findViewById(R.id.num8_btn);
        num9 = (Button) findViewById(R.id.num9_btn);
        //运算符
        add = (Button) findViewById(R.id.add_btn);
        sub = (Button) findViewById(R.id.sub_btn);
        multiply = (Button) findViewById(R.id.multiply_btn);
        divide = (Button) findViewById(R.id.divide_btn);
        bt_sqrt = (Button) findViewById(R.id.sqrt_btn);
        equal = (Button) findViewById(R.id.equal_btn);
        //其他
        clear = (Button) findViewById(R.id.clear_btn);
        delete = (Button) findViewById(R.id.delete_btn);
        change = (Button) findViewById(R.id.change_btn);
        point = (Button) findViewById(R.id.point_btn);
        //文本
        inputText = (TextView) findViewById(R.id.input_text);
        outputText = (TextView) findViewById(R.id.output_text);////////////////////////////////////201804241249
        existsText = inputText.getText().toString();
        /* * * * * 初始化控件 * * * * */

        /* * * * * 初始化事件 * * * * */
        //数字
        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        //运算符
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        bt_sqrt.setOnClickListener(this);
        equal.setOnClickListener(this);
        //其他
        clear.setOnClickListener(this);
        delete.setOnClickListener(this);
        change.setOnClickListener(this);
        point.setOnClickListener(this);
        /* * * * * 初始化事件 * * * * */
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.num0_btn:
                existsText = isOverRange(existsText, "0");
                break;

            case R.id.num1_btn:
                existsText = isOverRange(existsText, "1");
                break;

            case R.id.num2_btn:
                existsText = isOverRange(existsText, "2");
                break;

            case R.id.num3_btn:
                existsText = isOverRange(existsText, "3");
                break;

            case R.id.num4_btn:
                existsText = isOverRange(existsText, "4");
                break;

            case R.id.num5_btn:
                existsText = isOverRange(existsText, "5");
                break;

            case R.id.num6_btn:
                existsText = isOverRange(existsText, "6");
                break;

            case R.id.num7_btn:
                existsText = isOverRange(existsText, "7");
                break;

            case R.id.num8_btn:
                existsText = isOverRange(existsText, "8");
                break;

            case R.id.num9_btn:
                existsText = isOverRange(existsText, "9");
                break;

            case R.id.clear_btn:
                flag_counted = false;
                existsText = "0";
                inputText.setText(existsText);
                outputText.setText(existsText);
                break;

            case R.id.delete_btn:
                if (existsText.length() > 0) {
                    if (existsText.length() == 1) {
                        existsText = "0";
                    } else {
                        existsText = existsText.substring(0, existsText.length() - 1);
                    }
                }
                break;

            case R.id.add_btn:
                existsText = getResult();
                if (existsText.equals("Error")) {
                    existsText = "0";
                } else if (existsText.contains("+") || existsText.contains("—") || existsText.contains("×") || existsText.contains("÷") || existsText.contains("√")) {
                    return;
                } else {
                    existsText += "+";
                }
                if (flag_counted) {
                    flag_counted = false;
                }
                break;

            case R.id.sub_btn:
                existsText = getResult();
                if (existsText.equals("Error")) {
                    existsText = "0";
                } else if (existsText.contains("+") || existsText.contains("—") || existsText.contains("×") || existsText.contains("÷") || existsText.contains("√")) {
                    return;
                } else {
                    existsText += "—";
                }
                if (flag_counted) {
                    flag_counted = false;
                }
                break;

            case R.id.multiply_btn:
                existsText = getResult();
                if (existsText.equals("Error")) {
                    existsText = "0";
                } else if (existsText.contains("+") || existsText.contains("—") || existsText.contains("×") || existsText.contains("÷") || existsText.contains("√")) {
                    return;
                } else {
                    existsText += "×";
                }
                if (flag_counted) {
                    flag_counted = false;
                }
                break;

            case R.id.divide_btn:
                existsText = getResult();
                if (existsText.equals("Error")) {
                    existsText = "0";
                } else if (existsText.contains("+") || existsText.contains("—") || existsText.contains("×") || existsText.contains("÷") || existsText.contains("√")) {
                    return;
                } else {
                    existsText += "÷";
                }
                if (flag_counted) {
                    flag_counted = false;
                }
                break;

            case R.id.sqrt_btn:
                existsText = getResult();
                if (existsText.equals("Error")) {
                    existsText = "0";
                } else if (existsText.contains("+") || existsText.contains("—") || existsText.contains("×") || existsText.contains("÷") || existsText.contains("√")) {
                    return;
                } else {
                    existsText += "√";
                }
                if (flag_counted) {
                    flag_counted = false;
                }
                break;

            case R.id.point_btn:
                if (!flag_counted) {
                    //包括运算符时 判断第二个数字
                    if (existsText.contains("+") || existsText.contains("—") || existsText.contains("×") || existsText.contains("÷")) {
                        if (existsText.contains("+")) {
                            param1 = existsText.substring(0, existsText.indexOf("+"));
                            param2 = existsText.substring(existsText.indexOf("+") + 1);
                        } else if (existsText.contains("×")) {
                            param1 = existsText.substring(0, existsText.indexOf("×"));
                            param2 = existsText.substring(existsText.indexOf("×") + 1);
                        } else if (existsText.contains("÷")) {
                            param1 = existsText.substring(0, existsText.indexOf("÷"));
                            param2 = existsText.substring(existsText.indexOf("÷") + 1);
                        } else if (existsText.contains("—")) {
                            param1 = existsText.substring(0, existsText.indexOf("—"));
                            param2 = existsText.substring(existsText.indexOf("—") + 1);
                        }
                        //9位数字 包含.10位
                        boolean isContainedDot = param2.contains(".");
                        if (param2.length() == 9) {
                            if (param2.contains(".")) {
                                Toast.makeText(this, "已有小数点", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "最大位数(9)", Toast.LENGTH_SHORT).show();
                            }
                        } else if (param2.length() > 9) {
                            Toast.makeText(this, "已有小数点", Toast.LENGTH_SHORT).show();
                        } else if (!isContainedDot) {
                            if (param2.equals("")) {
                                existsText += "0.";
                            } else {
                                existsText += ".";
                            }
                        } else {
                            Toast.makeText(this, "已有小数点", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else {
                        //判断整个数字 9位数字 包含.10位
                        boolean isContainedDot = existsText.contains(".");
                        if (existsText.length() == 9) {
                            if (existsText.contains(".")) {
                                Toast.makeText(this, "已有小数点", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "最大位数(9)", Toast.LENGTH_SHORT).show();
                            }
                        } else if (existsText.length() > 9) {
                            Toast.makeText(this, "已有小数点", Toast.LENGTH_SHORT).show();
                        } else if (existsText.contains("√")) {
                            Toast.makeText(this, "仅有一个参数", Toast.LENGTH_SHORT).show();
                        } else if (!isContainedDot) {
                            existsText += ".";
                        } else {
                            Toast.makeText(this, "已有小数点", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    flag_counted = false;
                } else {
                    existsText = "0.";
                    flag_counted = false;
                }
                break;

            case R.id.equal_btn:
                existsText = getResult();
                if (existsText.equals("Error")) {
                    existsText = "0";
                }
                flag_counted = true;
                break;

            case R.id.change_btn://////////////////////////////////////////////////////////////////201804242109
                if (!flag_counted) {
                    //包括运算符时 判断第二个数字
                    if (existsText.contains("+") || existsText.contains("—") || existsText.contains("×") || existsText.contains("÷")) {
                        if (existsText.contains("+")) {
                            param1 = existsText.substring(0, existsText.indexOf("+"));
                            param2 = existsText.substring(existsText.indexOf("+") + 1);
                            param1 += "+";
                        } else if (existsText.contains("×")) {
                            param1 = existsText.substring(0, existsText.indexOf("×"));
                            param2 = existsText.substring(existsText.indexOf("×") + 1);
                            param1 += "×";
                        } else if (existsText.contains("÷")) {
                            param1 = existsText.substring(0, existsText.indexOf("÷"));
                            param2 = existsText.substring(existsText.indexOf("÷") + 1);
                            param1 += "÷";
                        } else if (existsText.contains("—")) {
                            param1 = existsText.substring(0, existsText.indexOf("—"));
                            param2 = existsText.substring(existsText.indexOf("—") + 1);
                            param1 += "—";
                        }
                        if (param2.equals("0") || param2.equals("")) {

                        } else if (param2.charAt(0) == '-') {
                            existsText = param1 + param2.substring(1, param2.length());
                        } else {
                            existsText = param1 + "-" + param2;
                        }
                    } else {
                        //判断整个数字
                        if (existsText.equals("0")) {

                        } else if (existsText.charAt(0) == '-') {
                            existsText = existsText.substring(1, existsText.length());
                        } else {
                            existsText = "-" + existsText;
                        }
                        flag_counted = false;
                    }
                } else {
                    flag_counted = false;
                }
                break;

        }
        inputText.setText(existsText);
    }

    /*判断数字*/
    private String isOverRange(String existsText, String s) {
        if (!flag_counted) {//判断是否计算过
            if (existsText.equals("0")) {
                existsText = "";
            }
            //包括运算符时 判断第二个数字
            if (existsText.contains("+") || existsText.contains("—") || existsText.contains("×") || existsText.contains("÷")) {
                String param2 = null;
                if (existsText.contains("+")) {
                    param2 = existsText.substring(existsText.indexOf("+") + 1);
                } else if (existsText.contains("—")) {
                    param2 = existsText.substring(existsText.indexOf("—") + 1);
                } else if (existsText.contains("×")) {
                    param2 = existsText.substring(existsText.indexOf("×") + 1);
                } else if (existsText.contains("÷")) {
                    param2 = existsText.substring(existsText.indexOf("÷") + 1);
                }

                if (existsText.substring(existsText.length() - 1).equals("+") ||
                        existsText.substring(existsText.length() - 1).equals("—") ||
                        existsText.substring(existsText.length() - 1).equals("×") ||
                        existsText.substring(existsText.length() - 1).equals("÷")) {
                    existsText += s;
                } else {
                    //判断整个数字 9位数字 包含.10位
                    if (param2.contains(".")) {
                        if (param2.length() >= 10) {
                            Toast.makeText(this, "最大位数(9)", Toast.LENGTH_SHORT).show();
                        } else {
                            existsText += s;
                        }
                    } else {
                        if (param2.length() >= 9) {
                            Toast.makeText(this, "最大位数(9)", Toast.LENGTH_SHORT).show();
                        } else {
                            existsText += s;
                        }
                    }
                }
            } else {
                //不包括运算符时 一个数字
                if (existsText.contains(".")) {
                    if (existsText.length() >= 10) {
                        Toast.makeText(this, "最大位数(9)", Toast.LENGTH_SHORT).show();
                    } else if (existsText.contains("√")) {
                        Toast.makeText(this, "仅有一个参数", Toast.LENGTH_SHORT).show();
                    } else {
                        existsText += s;
                    }
                } else {
                    if (existsText.length() >= 9) {
                        Toast.makeText(this, "最大位数(9)", Toast.LENGTH_SHORT).show();
                    } else if (existsText.contains("√")) {
                        Toast.makeText(this, "仅有一个参数", Toast.LENGTH_SHORT).show();
                    } else {
                        existsText += s;
                    }
                }
            }
            flag_counted = false;
        } else {
            existsText = s;
            flag_counted = false;
        }
        return existsText;
    }

    /*求值*/
    private String getResult() {
        /*
         * 如果有运算符，则进行运算
         * 没有运算符，则把已经存在的数据再传出去
         */
        if (existsText.contains("+")) {
            //获取两个参数
            param1 = existsText.substring(0, existsText.indexOf("+"));
            param2 = existsText.substring(existsText.indexOf("+") + 1);
            /*
             * 如果第二个参数为空，则还是显示当前字符
             */
            if (param1.equals("")) {
                param1 = "0";
            }
            if (param2.equals("")) {
                tempResult = existsText;
            } else {
                /*
                 * 转换String为Double
                 * 计算后再转换成String类型
                 * 进行正则表达式处理
                 */
                arg1 = Double.parseDouble(param1);
                arg2 = Double.parseDouble(param2);
                result = arg1 + arg2;
                tempResult = String.format("%f", result);
                tempResult = cut(tempResult);
                outputText.setText(tempResult);////////////////////////////////////////////////////201804241309
            }
        } else if (existsText.contains("×")) {

            param1 = existsText.substring(0, existsText.indexOf("×"));
            param2 = existsText.substring(existsText.indexOf("×") + 1);

            if (param1.equals("")) {
                param1 = "0";
            }
            if (param2.equals("")) {
                tempResult = existsText;
            } else {
                arg1 = Double.parseDouble(param1);
                arg2 = Double.parseDouble(param2);
                result = arg1 * arg2;
                tempResult = String.format("%f", result);
                tempResult = cut(tempResult);
                if (tempResult.equals("-0")) {
                    tempResult = "0";
                }
                outputText.setText(tempResult);////////////////////////////////////////////////////201804241309
            }
        } else if (existsText.contains("÷")) {

            param1 = existsText.substring(0, existsText.indexOf("÷"));
            param2 = existsText.substring(existsText.indexOf("÷") + 1);

            if (param1.equals("")) {
                param1 = "0";
            }
            if (param2.equals("0")) {
                tempResult = "Error";
                outputText.setText(tempResult);////////////////////////////////////////////////////201804241323
                Toast.makeText(this, "除数不能为0", Toast.LENGTH_SHORT).show();
            } else if (param2.equals("")) {
                tempResult = existsText;
            } else {
                arg1 = Double.parseDouble(param1);
                arg2 = Double.parseDouble(param2);
                result = arg1 / arg2;
                tempResult = String.format("%f", result);
                tempResult = cut(tempResult);
                if (tempResult.equals("-0")) {
                    tempResult = "0";
                }
                outputText.setText(tempResult);////////////////////////////////////////////////////201804241309
            }
        } else if (existsText.contains("√")) {

            param1 = existsText.substring(0, existsText.indexOf("√"));

            if (param1.equals("")) {
                param1 = "0";
            }
            arg1 = Double.parseDouble(param1);
            if (arg1 >= 0) {
                result = Math.sqrt(arg1);
                tempResult = String.format("%f", result);
                tempResult = cut(tempResult);
                outputText.setText(tempResult);////////////////////////////////////////////////////201804241309
            } else {
                tempResult = "Error";
                outputText.setText(tempResult);////////////////////////////////////////////////////201804241323
                Toast.makeText(this, "被开方数不能小于0", Toast.LENGTH_SHORT).show();
            }
        } else if (existsText.contains("—")) {

            param1 = existsText.substring(0, existsText.indexOf("—"));
            param2 = existsText.substring(existsText.indexOf("—") + 1);

            if (param1.equals("")) {
                param1 = "0";
            }
            if (param2.equals("")) {
                tempResult = existsText;
            } else {
                arg1 = Double.parseDouble(param1);
                arg2 = Double.parseDouble(param2);
                result = arg1 - arg2;
                tempResult = String.format("%f", result);//////////////////////////////////////////0424
                tempResult = cut(tempResult);
                outputText.setText(tempResult);////////////////////////////////////////////////////201804241309
            }
        } else {
            tempResult = existsText;
            tempResult = cut(tempResult);
            outputText.setText(tempResult);////////////////////////////////////////////////////////201804241303
        }
        return tempResult;
    }

    /*去掉多余的0和.*/
    public static String cut(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

}