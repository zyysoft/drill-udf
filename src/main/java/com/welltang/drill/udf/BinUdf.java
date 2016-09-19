package com.welltang.drill.udf;

import javax.inject.Inject;

import org.apache.drill.exec.expr.DrillSimpleFunc;
import org.apache.drill.exec.expr.annotations.FunctionTemplate;
import org.apache.drill.exec.expr.annotations.Output;
import org.apache.drill.exec.expr.annotations.Param;
import org.apache.drill.exec.expr.holders.NullableBigIntHolder;
import org.apache.drill.exec.expr.holders.VarCharHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.DrillBuf;

/**
 * 十进制转成二进制,使用方式bin(int);
 * 参数必须是int类型
 * @author geping
 *
 */
@FunctionTemplate(
        name = "bin", //函数名
        scope = FunctionTemplate.FunctionScope.SIMPLE, //函数类型
        nulls = FunctionTemplate.NullHandling.NULL_IF_NULL //值为null时的返回值
)
public class BinUdf implements DrillSimpleFunc
{
	public static Logger logger = LoggerFactory.getLogger(BinUdf.class);
			
	@Param
	NullableBigIntHolder input;
    @Output
    VarCharHolder out;
    @Inject
    DrillBuf buffer; 

	@Override
	public void setup() {
		// TODO Auto-generated method stub
	}

	@Override
	public void eval() {
		// get the value and replace with
		String outputValue="0";
		try {
			long origin = input.value;
			outputValue= Long.toBinaryString(origin);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.toString());
			e.printStackTrace();
		}
		buffer.setBytes(0, outputValue.getBytes());
	    out.buffer = buffer;
	    out.start = 0;
	    out.end = outputValue.getBytes().length;
	}
}
