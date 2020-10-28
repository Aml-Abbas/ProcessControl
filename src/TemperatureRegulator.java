public class TemperatureRegulator extends Thread{
    private ProcessControl processControl;
    private double targetTemp;
    private double currentTemp;
    private boolean heating;

    public TemperatureRegulator(ProcessControl processControl){
        this.processControl= processControl;
    }

    public void run(){
        while (true){

            try {
                targetTemp= processControl.targetTemp();
                currentTemp= processControl.readTemp();

                if (currentTemp<=targetTemp-4.8 && !heating){
                    processControl.heating(true);
                    heating= true;
                }
                if (currentTemp>=targetTemp-1 && heating){
                    processControl.heating(false);
                    heating= false;
                }

                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
