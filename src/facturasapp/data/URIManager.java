/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturasapp.data;

/**
 *
 * @author CiberXtrem
 */
public class URIManager {
    public static void main(String [] argvs) {

    	browse("www.yahoo.es");
    	/*String [] args = {"www.yahoo.es","www.tiscali.es"};

        if( !java.awt.Desktop.isDesktopSupported() ) {

            System.err.println( "Desktop is not supported (fatal)" );
            System.exit( 1 );
        }

        if ( args.length == 0 ) {

            System.out.println( "Usage: OpenURI [URI [URI ... ]]" );
            System.exit( 0 );
        }

        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

        if( !desktop.isSupported( java.awt.Desktop.Action.BROWSE ) ) {

            System.err.println( "Desktop doesn't support the browse action (fatal)" );
            System.exit( 1 );
        }

        for ( String arg : args ) {

            try {

                java.net.URI uri = new java.net.URI( arg );
                desktop.browse( uri );
            }
            catch ( Exception e ) {

                System.err.println( e.getMessage() );
            }
        }*/
    }
    public static void browse(String uri_str){
    	java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
    	try {

            java.net.URI uri = new java.net.URI( uri_str );
            desktop.browse( uri );
        }
        catch ( Exception e ) {

            System.err.println( e.getMessage() );
        }
    }

}
