package main.Serber;

/**
 *
 * @author saban
 */
public abstract class SerberTask implements Runnable{
    
    private boolean running = false;
    
    public boolean __run(){
        if(running)return false;
        
        setRunning(true);
        new Thread(this).start();
        
        return running;
    }
    
    public void __stop(){
        setRunning(false);
    }

    private void setRunning(boolean b) {
        this.running = b;
    }
    
    public boolean isRunning() {
        return running;
    }
    
}
