package socket.rpc.provider;

public class BatterCakeServiceImpl implements BatterCakeService {

	@Override
	public String sellBatterCake(String name) {
		return name + "煎饼,卖的特别好";
	}

}