import java.util.Scanner;
public class BracketBalance
{
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
  		Stack miPila = new Stack<>();
  		String cadenaUsuario;
  		Scanner miLector = new Scanner(System.in);
  		
  		String carsApertura ="([{";
  		
  		//Pedir cadena al usuario:
  		System.out.print("Ingrese una cadena para examinar si los paréntesis están balanceados");
  		cadenaUsuario = miLector.next();
  		
  		char carUsuario, carPila;
  		
  		//recorrer todos los caracteres de una cadena:
  		for(int i=0; i < cadenaUsuario.length(); i++)
  		{
  			carUsuario = cadenaUsuario.charAt(i);
  			//mirar si es un caracter de apertura:
  			if(carsApertura.contains(String.valueOf(carUsuario)))
  			{
  				miPila.push(new Node(carUsuario));	
  			}
  			else
  			{
  				//??
  				carPila = (Character) miPila.peek().getData();
  				//
  				if(carPila == '(' && carUsuario==')' ||
  				   carPila == '[' && carUsuario == ']' ||
  				   carPila == '{' && carUsuario == '}')
  				{
  					miPila.pop();
  				}
  				else
  					miPila.push(new Node(carUsuario));
  			}			
  		}
  		if(miPila.isEmpty())
  			System.out.print("La cadena está balanceada");
  		else
  			System.out.print("La cadena no está balanceada");

	}
}
