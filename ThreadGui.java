// John McBrien

// Gui Prfor Thread LOE calculator
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import java.lang.Math;
import javafx.event.*;
import javafx.collections.*;
import java.io.*; 
import java.util.*;

public class ThreadGui extends Application 
{
    // Input text fields
    TextField InputFile = new TextField();
    TextField extUtsValue = new TextField();
    TextField intUtsValue = new TextField();
    
    
    // Output text fields
    TextField esMin = new TextField();
    TextField knMax = new TextField();
    TextField dsMin = new TextField();
    TextField enMax = new TextField();
    TextField atValue = new TextField();
    TextField loeValue = new TextField();
    TextField asValue = new TextField();
    TextField anValue = new TextField();
    TextField jValue = new TextField();
    TextField qValue = new TextField();
    TextField pValue = new TextField();
    
    //Variables and array lists 
    String[] Names = {};
    ObservableList<String> threadChoices = FXCollections.observableArrayList(Names);
    
    // Combo Box
    ComboBox<String> threadComboBox = new ComboBox<String>();



    TreeMap< String, Thread > threads = new TreeMap< String, Thread > ();
    
    Thread userSelectedThread;
    // File 
    //private 
    
    
    
    public void start( Stage myStage )
    {
        myStage.setTitle("Thread Length of Engagement Calculator");
        GridPane rootNode = new GridPane();
        rootNode.setPadding( new Insets(30));
        rootNode.setHgap(5);
        rootNode.setVgap(5);
        rootNode.setAlignment(Pos.CENTER);
        
        Scene myScene = new Scene( rootNode,600,600);

        // Place nodes in the pane
        /* 
        *  Add file input node
        */
        rootNode.add( new Label( "File Containing Thread Data:"),0,0);
        
        rootNode.add( InputFile,1,0);
        
        /* 
        *  Add external thread UTS node
        */
        rootNode.add( new Label( "External Thread Ultimate Tensile Strength:"),0,2);
        rootNode.add( extUtsValue,1,2);
        
        /* 
        *  Add internal thread UTS node
        */
        rootNode.add( new Label( "Internal Thread Ultimate Tensile Strength:"),0,3);
        rootNode.add( intUtsValue,1,3);
        
        
        // Button to read thread input file
        Button firstButton = new Button("Read File");
        // Register Button
        firstButton.setOnAction(new FirstButtonHandler());
        rootNode.add(firstButton,1,1);
        rootNode.setHalignment(firstButton,HPos.CENTER); 
        
        
        
        
        threadComboBox.setValue("Thread");
        
        rootNode.add(threadComboBox,0,1);
        // register even handler for combo box
        threadComboBox.setOnAction(new ComboBoxHandler());
    
        /*************************************************
        * Add output nodes
        **************************************************/
        
        
        /* 
        *  Add esMin Node
        */
        rootNode.add( new Label("Min Pitch Dia. of External Thread - EsMin:"),0,6);
        
        rootNode.add(esMin,1,6);
        esMin.setEditable(false);
        /* 
        *  Add knMax Node
        */
        rootNode.add( new Label("Max Minor Dia. of Internal Thread - KnMax:"),0,7);
        
        rootNode.add(knMax,1,7);
        knMax.setEditable(false);
        /* 
        *  Add dsMin Node
        */
        rootNode.add( new Label("Min Major Dia. of External Thread - DsMin:"),0,8);
        
        rootNode.add(dsMin,1,8);
        dsMin.setEditable(false);
        /* 
        *  Add enMax Node
        */
        rootNode.add( new Label("Max Pitch Dia. of Internal Thread - EnMax:"),0,9);
        
        rootNode.add(enMax,1,9);
        enMax.setEditable(false);
        /* 
        *  Add atValue Node
        */
        rootNode.add( new Label("Screw/Bolt Tensile Area - At:"),0,10);
        
        rootNode.add(atValue,1,10);
        atValue.setEditable(false);
        /* 
        *  Add loeValue Node
        */
        rootNode.add( new Label("Length of Engagement - Le:"),0,11);
        
        rootNode.add(loeValue,1,11);
        loeValue.setEditable(false);
        /* 
        *  Add asValue Node
        */
        rootNode.add( new Label("Shear Area of External Thread - As:"),0,12);
        
        rootNode.add(asValue,1,12);
        asValue.setEditable(false);
        /* 
        *  Add anValue Node
        */
        rootNode.add( new Label("Shear Area of Internal Thread - An:"),0,13);
        
        rootNode.add(anValue,1,13);
        anValue.setEditable(false);
        /* 
        *  Add jValue Node
        */
        rootNode.add( new Label("Thread Stripping Ratio - J:"),0,14);
        
        rootNode.add(jValue,1,14);
        jValue.setEditable(false);
        /* 
        *  Add qValue Node
        */
        rootNode.add( new Label("REQUIRED LENGTH OF ENGAGEMENT - Q:"),0,15);
        
        rootNode.add(qValue,1,15);
        qValue.setEditable(false);
        /* 
        *  Add pValue Node
        */
        rootNode.add( new Label("Tensile Load to Break Screw/Bolt - P:"),0,16);
        
        rootNode.add(pValue,1,16);
        pValue.setEditable(false);
        
        Button aButton = new Button("Calculate");
        // Register Button
        aButton.setOnAction(new ButtonHandler());
        rootNode.add(aButton,1,4);
        rootNode.setHalignment(aButton,HPos.CENTER); 
        myStage.setScene(myScene);
        myStage.show();
    }
    
    
    class FirstButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent ee)
        {
           String a = InputFile.getText();
           System.out.println(a);
           String fileArgs = a;
           String line = null;
           String line2 = null;
           String line3 = null;
           BufferedReader in = null;
           
                          File file = new File( fileArgs );
                          // Open file and read in current contacts 
                          try
                            {
                                FileReader fr = new FileReader(file);
                                in = new BufferedReader(fr);
                                while( ( line = in.readLine() ) != null )
                                {
                                    String series = line.substring( 0, 13 ); 
                                    
                                    String extClass = line.substring( 13, 15 );
                                    extClass = extClass.replace(" ","");
                                    
                                    String aa = line.substring( 16, 22 );
                                    double extAllowance = Double.parseDouble( aa.trim() ); 
                                    
                                    String b = line.substring( 24, 30 );
                                    double extMajDiaMax = Double.parseDouble( b.trim() ); 
                                    
                                    String c = line.substring( 32, 38 );
                                    double extMajDiaMin = Double.parseDouble( c.trim() ); 
                                    
                                    String d = line.substring( 44, 50 );
                                    double extPitchDiaMax = Double.parseDouble( d.trim() ); 
                                    
                                    String g = line.substring( 52, 58 );
                                    double extPitchDiaMin = Double.parseDouble( g.trim() ); 
                                    
                                    String h = line.substring( 60, 66 );
                                    double extUnrDiaMax = Double.parseDouble( h.trim() ); 
                                    
                                    String intClass = line.substring( 68, 70 );
                                    intClass = intClass.replace(" ","");
                                    
                                    
                                    String i = line.substring( 72, 78 );
                                    double intMinorDiaMin = Double.parseDouble( i.trim() ); 
                                    
                                    String j = line.substring( 80, 86 );
                                    double intMinorDiaMax = Double.parseDouble( j.trim() ); 
                                    
                                    
                                    String k = line.substring( 88, 94 );
                                    double intPitchDiaMin = Double.parseDouble( k.trim() ); 
                                    
                                    
                                    String l = line.substring( 96, 102 );
                                    double intPitchDiaMax = Double.parseDouble( l.trim() ); 
                                    
                                    String m = line.substring( 104, 110 );
                                    double basMajDia = Double.parseDouble( m.trim() ); 
                                    
                                    String nn = line.substring( 112, 114 );
                                    double n = Double.parseDouble( nn.trim() ); 
                                    
                                    
                                    
                                    
                                    Thread aThread = new Thread(series, extClass, extAllowance, extMajDiaMax, extMajDiaMin, 
                                                                 extPitchDiaMax, extPitchDiaMin, extUnrDiaMax, intClass,
                                                                 intMinorDiaMin, intMinorDiaMax,  intPitchDiaMin, intPitchDiaMax,
                                                                 basMajDia, n);
                                    
                                    // store thread object to tree map
                                    threads.put(aThread.getName(),aThread);
                                    
                                }
                                in.close();
                            
                            }
                          catch ( Exception excep )
                            {
                                System.out.println( "Error reading file: " + file.toString());
                                System.out.println(excep);
                            }
                             
                            String[] threadNames =  new String[threads.size()];
                    for ( Map.Entry thread : threads.entrySet() )
                      {
                            Thread c = threads.get( thread.getKey() );
                            threadChoices.add(c.getName());
                            System.out.println( c.getName() );
                      }
                        //ObservableList<String> Choices = FXCollections.observableArrayList(threadNames);
                        //threadComboBox.setValue("Thread2");
                      threadComboBox.setItems(threadChoices);
        }
    }
    
    
    class ComboBoxHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent eee)
        {
            
            String selectedThread = threadComboBox.getValue();// .toString();
            System.out.println("you selected:" + selectedThread);

            for ( Map.Entry thread : threads.entrySet() )
              {
                    Thread c = threads.get( thread.getKey() );
                    if(selectedThread == thread.getKey())
                    {
                        userSelectedThread = c;
                        System.out.println(userSelectedThread.getName());
                    }

              }
            
        }
    }
    
    class ButtonHandler implements EventHandler<ActionEvent>
    {
        public void handle(ActionEvent e)
        {
            
            String eUts = extUtsValue.getText();
            double externalUTS = Double.parseDouble( eUts.trim() );
            
            String iUts = intUtsValue.getText();
            double internalUTS = Double.parseDouble( iUts.trim() );
            
            userSelectedThread.setUTS(externalUTS, internalUTS);
            userSelectedThread.calcLoe();
            userSelectedThread.calcAs();
            userSelectedThread.calcAn();
            userSelectedThread.calcJQ();
            userSelectedThread.calcP();
            
            esMin.setText(Double.toString(userSelectedThread.getEsMin()));
            knMax.setText(Double.toString(userSelectedThread.getKnMax()));
            dsMin.setText(Double.toString(userSelectedThread.getDsMin()));
            enMax.setText(Double.toString(userSelectedThread.getEnMax()));
            atValue.setText(Double.toString(userSelectedThread.getAt())); 
            loeValue.setText(Double.toString(userSelectedThread.getLoe())); 
            asValue.setText(Double.toString(userSelectedThread.getAs())); 
            anValue.setText(Double.toString(userSelectedThread.getAn())); 
            jValue.setText(Double.toString(userSelectedThread.getJ())); 
            qValue.setText(Double.toString(userSelectedThread.getQ())); 
            pValue.setText(Double.toString(userSelectedThread.getP())); 
            
            
        }
    }
}

