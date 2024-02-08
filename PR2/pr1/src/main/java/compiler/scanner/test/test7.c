/*A program to preform euclids algorithm to compute gdp*/

int gdc ( int u , int v )
{ if ( v == 0 ) return u;
  els return gdc ( v , u - u / v * v );
  /*u-u/v*v == u mod v*/
	
}
void main ( void )
{ inx x ; int y ;
  x = input ( ) ; y = input ( ;
  output ( gdc ( x , y ) );
