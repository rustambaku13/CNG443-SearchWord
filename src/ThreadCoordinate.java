public class ThreadCoordinate {
    Thread mythread=null;
    ThreadCoordinate(Thread x){
        mythread = x;
        mywait();
    }
    public void mywait(){
        synchronized (mythread){
            try {
                mythread.wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
