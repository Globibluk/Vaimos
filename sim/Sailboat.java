package sim;

public class Sailboat {
	
	double x,y,theta,v,omega,phi,phiPoint; 									// state variables
    double beta, Jz, rg, rv, alphag, alphav, alphaf, alphatheta, l,m, Jx; 	//parameters
    double a,psi;  															//wind
    double fg,fv,gamma,deltav,deltag,deltavmax;  							//link variables
    double eta;																//viscosité
    double hv; 																// hauteur de centre de poussée

    double ax,ay,bx,by; 													// point de l'axe a-b
    int q;
    
    int dt = 1; 															// A changer
    
    public Sailboat(int ax, int bx, int ay, int by)
    {
    	
    	x=1.0; 
    	y=1.0;
    	theta =0.0;
    	v = 0.1;
    	omega = 0.0;
    	phi = 0;
    	phiPoint = 0;
        Jx = 3000.0;
        Jz = 10000.0; 														// moments d'inertie
        beta=0.1;
        rg=1;
        alphatheta=6000;
        m=300.0;
        alphaf=1.0;
        rv=1.0;
        alphag=2000.0;
        l=1.0;
        alphav=1000.0;
        a=1.0;
        psi= 0;  															//vent
        hv = 1.00;  														//Roulis
        this.ax= 0;															//line
        this.ay= 0;															//line
        this.bx= 20;														//line
        this.by= 0;    													//line
        q=1;
    }
    
    public void setLine(int ax, int bx, int ay, int by)
    {
    	this.ax = ax;
    	this.bx = bx;
    	this.ay = ay;
    	this.by = by;
    }
    
    public int sign(double a)
    {
    	if (a>0) return 1;
    	else return -1;
    }
    
    public void setUp()
    {
    	double r=10;
        double zeta=Math.PI/4;
        double e=((bx-ax)*(y-ay)-(x-ax)*(by-ay))/Math.hypot(ax-bx,ay-by);
        if (Math.abs(e)>r) q=0;  //The robot is now free from its closed-hauled mode
        double phi=Math.atan2(by-ay,bx-ax);
        double thetabar=phi-0.5*Math.atan(e/r);
        if ((q==0)&((Math.cos(psi-thetabar)+Math.cos(zeta)<0)|((Math.abs(e)<r)&(Math.cos(psi-phi)+Math.cos(zeta)<0)))) q=sign(e);
        if (q!=0)  thetabar=Math.PI+psi-zeta*q;
        double dtheta=theta-thetabar;
        deltag=(1/Math.PI)*(Math.atan(Math.tan(0.5*dtheta)));
        deltavmax=0.5*Math.PI*(0.5*(Math.cos(psi-thetabar)+1));
    }
    
    public void update()
    {
    	setUp();
        double xw_ap=a*Math.cos(psi-theta)-v;
        double yw_ap=a*Math.sin(psi-theta);
        double psi_ap=Math.atan2(yw_ap,xw_ap);   //Apparent wind
        double a_ap=Math.sqrt(xw_ap*xw_ap+yw_ap*yw_ap);
        gamma=Math.cos(psi_ap)+Math.cos(deltavmax);
        if (gamma<0) {deltav=Math.PI+psi_ap;} //voile en drapeau
        else  if (Math.sin(-psi_ap)>0) deltav=deltavmax;   else deltav=-deltavmax;
        fg = alphag*v*Math.sin(deltag);
        fv = alphav*a_ap*Math.sin(deltav-psi_ap);
        x += (v*Math.cos(theta)+beta*a*Math.cos(psi))*dt;
        y += (v*Math.sin(theta)+beta*a*Math.sin(psi))*dt;
        theta += omega*dt;
        omega += (1/Jz)*((l-rv*Math.cos(deltav))*fv-rg*Math.cos(deltag)*fg-alphatheta*omega*v)*dt;
        v     += (1/m)*(Math.sin(deltav)*fv-Math.sin(deltag)*fg-alphaf*v*v)*dt;
        phiPoint += (-phiPoint+fv*hv*Math.cos(deltav)*Math.cos(phi)/Jx - 10000*9.81*Math.sin(phi)/Jx)*dt ;
        phi += phiPoint * dt;
    }
    
    public double getX()
    {
    	return x;
    }
    
    public double getY()
    {
    	return y;
    }
}