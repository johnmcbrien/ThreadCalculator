import java.lang.*;

public class Thread
{
    private String series;
    private String extClass;
    private String key;
    private double extAllowance;
    private double extMajDiaMax;
    private double extMajDiaMin;
    private double extPitchDiaMax;
    private double extPitchDiaMin;
    private double extUnrDiaMax;
    private String intClass;
    private double intMinorDiaMin;
    private double intMinorDiaMax;
    private double intPitchDiaMin;
    private double intPitchDiaMax;
    private double basMajDia;
    private double n;
    
    private double extAs;
    private double intAn;
    
    private double extUts;
    private double intUts;
    
    private double extAt;
    
    private double loe;
    
    private double J;
    private double Q;
    // load that breaks the external bolt/screw
    private double P;
    
    // Class Constructor 
    public Thread(String series, String extClass, double extAllowance, double extMajDiaMax, double extMajDiaMin, 
                        double extPitchDiaMax, double extPitchDiaMin, double extUnrDiaMax, String intClass,
                        double intMinorDiaMin, double intMinorDiaMax, double intPitchDiaMin, double intPitchDiaMax,
                        double basMajDia, double n)
    {
        this.series = series;
        this.extClass = extClass;
        this.extAllowance = extAllowance;
        this.extMajDiaMax = extMajDiaMax;
        this.extMajDiaMin = extMajDiaMin;
        this.extPitchDiaMax = extPitchDiaMax;
        this.extPitchDiaMin = extPitchDiaMin;
        this.extUnrDiaMax = extUnrDiaMax;
        this.intClass = intClass;
        this.intMinorDiaMin = intMinorDiaMin;
        this.intMinorDiaMax = intMinorDiaMax;
        this.intPitchDiaMin = intPitchDiaMin;
        this.intPitchDiaMax = intPitchDiaMax;
        this.basMajDia = basMajDia;
        this.n = n;
        this.key = this.series + this.extClass;
        
    }
    
    //Setters
    
    public void setUTS(double extUts, double intUts)
    {
        this.extUts = extUts;
        this.intUts = intUts;
    }
    
    //  Methods
     
    private void calcAt(double extUts)
    {
        double D = this.basMajDia;
        
        if(extUts < 180000)
        {
            this.extAt = 0.7854*(D - 0.9743/this.n)*(D - 0.9743/this.n);
        }
        else
        {
            double esMin = this.extPitchDiaMin;
            this.extAt = 3.1416*(esMin/2.0000 - 0.16238/this.n)*(esMin/2.0000 - 0.16238/this.n);
        }
        
    }
    
    public void calcLoe() 
    {
        double esMin = this.extPitchDiaMin;
        double knMax = this.intMinorDiaMax;
        
        
        calcAt(this.extUts);
        this.loe = (2.0000*this.extAt)/(3.1416*knMax*(0.5000+0.57735*this.n*(esMin - knMax)));
    }
    
    public void calcAs()
    {
        double esMin = this.extPitchDiaMin;
        double knMax = this.intMinorDiaMax;
        
        this.extAs = 3.1416*this.n*this.loe*knMax*(1.0000/(2.0000*this.n)+0.57735*(esMin - knMax));
    }
    
    public void calcAn()
    {
        double dsMin = this.extMajDiaMin;
        double enMax = this.intPitchDiaMax;
        
        this.intAn = 3.1416*this.n*this.loe*dsMin*(1.0000/(2.0000*this.n)+0.57735*(dsMin - enMax));
    }
    
     public void calcJQ()
     {
          this.J = this.extAs*this.extUts/(this.intAn*this.intUts);
          this.Q = this.J*this.loe;
     }
     
     public void calcP()
     {
         this.P = this.extUts * this.extAt;
     }
     //Getters
    
    public double getEsMin()
    {
        return Math.round((this.extPitchDiaMin*10000.0000))/10000.0000;
    }
    
    public double getKnMax()
    {
        return Math.round(this.intMinorDiaMax*10000.0000)/10000.0000;
    }
    
    public double getDsMin()
    {
        return Math.round(this.extMajDiaMin*10000.0000)/10000.0000;
    }
    
    public double getEnMax()
    {
        return Math.round(this.intPitchDiaMax*10000.0000)/10000.0000;
    }
    
    
    
    public double getJ()
    {
        return Math.round(this.J*10000.0000)/10000.0000;
    }
    
    public double getQ()
    {
        return Math.round(this.Q*10000.0000)/10000.0000;
    }
    
    public double getLoe()
    {
        return Math.round(this.loe*10000.0000)/10000.0000;
    }
    
    public double getP()
    {
        return Math.round(this.P*10000.0000)/10000.0000;
    }
    
    public double getAt()
    {
        return Math.round(this.extAt*10000.0000)/10000.0000;
    }
    
    public double getAs()
    {
        return Math.round(this.extAs*10000.0000)/10000.0000;
    }
    
    public double getAn()
    {
        return Math.round(this.intAn*10000.0000)/10000.0000;
    }
    
    public double getExtUts()
    {
        return Math.round(this.extUts*10000.0000)/10000.0000;
    }
    
    public double getIntUts()
    {
        return Math.round(this.intUts*10000.0000)/10000.0000;
    }
    
    public String getName()
    {
        return this.key;
    }
    
}

