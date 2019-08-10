
public class SingletonDemo {

    private volatile static SingletonDemo singletonDemo = null;

    private SingletonDemo(){};

    private SingletonDemo getInstance() {
        if (singletonDemo == null) {
            synchronized (this) {
                if(singletonDemo == null){
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }
}
