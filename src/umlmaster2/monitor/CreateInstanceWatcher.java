package umlmaster2.monitor;

public class CreateInstanceWatcher implements subscription{

	private subscribed sub;
	
	@Override
	public subscribed ConsulteInstance(String str) {
	   if (str.equals(sub.getClass().getSimpleName())){
		   
	        return sub;	
	   }
		else{
			return null;
		}
	}

	@Override
	public void NotifyInstance(subscribed sub) {
		this.sub = sub;
		
	}
}