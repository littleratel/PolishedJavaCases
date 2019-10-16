/**
 * 实际的应用场景中，我们很可能需要一些同步处理，需要同步等待服务端将我的消息处理完成后再进行下一步处理。
 * 这相当于RPC(Remote Procedure Call，远程过程调用).
 * 
 * RabbitMQ中也支持RPC,具体实现如下：
 * 1.客户端发送请求（消息）时，在消息的属性中设置两个值replyTo（一个Queue_Name，用于告诉服务器处理完成后，将通知我的消息发送到这个Queue中）
 * 和correlationId（此次请求的标识号，服务器处理完成后需要将此属性返还，客户端将根据这个id了解哪条请求被成功执行了或执行失败）;
 * 2.服务器端收到消息并处理;
 * 3.服务器端处理完消息后，将生成一条应答消息到replyTo指定的Queue，同时带上correlationId属性;
 * 4.客户端之前已订阅replyTo指定的Queue，从中收到服务器的应答消息后，根据其中的correlationId属性分析哪条请求被执行了，根据执行结果进行后续业务处理
 * 
 */
package jms.rabbitmq.rpc;
