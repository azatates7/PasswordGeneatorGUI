/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 

import com.jfoenix.controls.JFXTextField; 
import java.net.URL;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author azatates7
 */
public class GeneratorController implements Initializable {
    
    String symbols = "ABCDEFGJKLMNPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzx*?-_+#0123456789";  
    SecureRandom sr = new SecureRandom();  
   
    @FXML private JFXTextField txtlength; 
    @FXML private JFXTextField txtspchar; 
    @FXML private Label lblmessage;
    String label = "Your Key = ";
  
    @FXML void generate(ActionEvent event) {  
        
      generatekey();
      
        } 
    
    @FXML void act1(MouseEvent event) {
        txtlength.setText("");
        lblmessage.setText(label);
    }

    @FXML void act2(MouseEvent event) {
        txtspchar.setText("");
        if(txtlength.getText().isEmpty()){
            lblmessage.setText("Please input length ! ");
        }
         Pattern regex = Pattern.compile("[0-9]*");
         Matcher m = regex.matcher(txtlength.getText()); 
         
         if(!m.matches()){
             lblmessage.setText("Wrong length type ! ");
         }
        
    }

    @Override 
public void initialize(URL location, ResourceBundle resources) {
         
    }

    private void generatekey() {

           try{
            char character = txtspchar.getText().charAt(0); 
            txtspchar.setText(String.valueOf(character));
            char specialcharacter = character; 
            
            int length = Integer.valueOf(txtlength.getText());
            if(length > 20 || length < 5){
                length = 8;
            }
            
            char[] buff = new char[length]; 
		
            for (int index = 0; index < buff.length; ++index) {
		 buff[index] = symbols.charAt(sr.nextInt(symbols.length())); 
		 buff[0] = specialcharacter;
		}
		       
            char aa[]  = buff;
            Character[] arr = new Character[buff.length];
            int i = 0;
            for(char value : aa) {
                arr[i++] = value;
		   } 
		   
            List<Character> chl = Arrays.asList(arr);
            Collections.shuffle(chl);
            StringBuilder sb = new StringBuilder();
            chl.forEach(sb::append);
            String key = sb.toString(); 
            lblmessage.setText(label+key);
            System.out.println("Your Key : "+key); 
            }
         
        catch(NumberFormatException ex){
            lblmessage.setText("Type Error");
        }
        
        catch(RuntimeException ex){
            lblmessage.setText("*No Input Given");
        }
        
    }
  
    }     