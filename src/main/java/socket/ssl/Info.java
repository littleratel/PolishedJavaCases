package socket.ssl;

public class Info {}

/**
 * server 与 client 之间、建立在TSL协议之上的安全通信.
 * 下面是借助 keytool 创建证书更一般过程：
    //先为 MyCA 生成一个自签名的根证书，
	keytool -genkeypair -alias MyCA -keyalg RSA -keystore ./ca_ks -dname "CN=ICA,OU=cn,O=cn,L=cn,ST=cn,C=cn" -storepass stmyca -keypass 123456
	查看证书仓库中证书
	keytool -list -v -alias MyCA -keystore ./ca_ks -storepass stmyca  #-v Certificate fingerprints: MD5/SHA1/SHA256/Signature algorithm name/Version
	keytool -list -rfc -alias MyCA -keystore ./ca_ks -storepass stmyca 
	
	
	//分别为server/client 生成自签名证书
	keytool -genkey -v -alias ssl-server -keyalg RSA -keystore ./server_ks -dname "CN=localhost,OU=cn,O=cn,L=cn,ST=cn,C=cn" -storepass server -keypass 123123
	为client产生一个证书
	keytool -genkey -v -alias ssl-client -keyalg RSA -keystore ./client_ks -dname "CN=localhost,OU=cn,O=cn,L=cn,ST=cn,C=cn" -storepass client -keypass 456456
	
	
	// 导出Root CA证书、导入到 server/client 证书库
	keytool -export -alias MyCA -keystore ./ca_ks -file rootCa.cer -storepass stmyca 
	keytool -import -trustcacerts -alias rootCa-crt -keystore ./server_ks -file rootCa.cer  -storepass server 
	keytool -import -trustcacerts -alias rootCa-crt -keystore ./client_ks -file rootCa.cer  -storepass client 
	
	
	// 用根证书MyCA 分别为 server 与 client 签名
	创建签名申请：
	服务端：
	keytool -certreq -alias ssl-server -keystore ./server_ks  -storepass server -keypass 123123 | tee ssl-server.certreq
	keytool -certreq -alias ssl-server -storepass server | tee ssl-server.certreq             #Alias <ssl-server> has no key
	keytool -certreq -keystore ./server_ks  -storepass server | tee ssl-server.certreq   #Alias <mykey> does not exist
	客户端：
	keytool -certreq -alias ssl-client -keystore ./client_ks  -storepass client -keypass 456456 | tee ssl-client.certreq
	keytool -certreq -alias ssl-client-req -keystore ./client_ks  -storepass client -keypass 456456  #Alias <ssl-client-req> does not exist
	
	
	// 使用RootCA处理这个请求，为其签名
	服务端：
	keytool -gencert -alias MyCA -keystore ./ca_ks -infile  ./ssl-server.certreq -outfile server.cer -storepass stmyca -keypass 123456  
	客户端：
	keytool -gencert -alias MyCA -keystore ./ca_ks -infile  ./ssl-client.certreq -outfile client.cer -storepass stmyca -keypass 123456  
	查看生成的证书
	keytool -printcert -v -file server.cer
	keytool -printcert -v -file client.cer
	
	
	// 将CA签名的server/client端证书分别导入到相应的client/server端的证书仓库
	keytool -import -trustcacerts -alias server-to-client-crt -file ./server.cer -keystore ./client_ks -storepass client 
	keytool -import -trustcacerts -alias client-to-server-crt -file ./client.cer -keystore ./server_ks -storepass server 
	
	
	// 查看证书仓库中所有证书：
	keytool -list -v -keystore ./server_ks -storepass server  
	查看证书alias对应的证书
	keytool -list -v -alias ssl-server-crt -keystore ./server_ks -storepass server
	keytool -list -v -alias ssl-client-crt  -keystore ./client_ks -storepass client
 * 
 * 
 * */
