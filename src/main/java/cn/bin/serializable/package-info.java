package cn.bin.serializable;
/**
 * 测试点:<br>
 * 1. Tx端与Rx端的UserInfo信息一致,但包名不一致,反序列化会失败,不能转换;<br>
 * 2. Tx端与Rx端的UserInfo信息一致,但serialVersionUID不一致, 不能转换;<br>
 * 3. Rx端比Tx端多一个字段，可以正常解析，但Rx端多出来的字段值为空或null，即便它在Rx端有默认值；<br>
 * 4. Rx端比Tx端少一个字段，可以正常解析，但是没法使用少的那个属性。<br>
 * 
 * 
 */