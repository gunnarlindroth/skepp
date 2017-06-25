package skepp;

public enum ShipType {
  
  CRUISER,   
  
  DESTROYER,
  
  FRIGATE,
  
  SUBMARINE;
  
  
  static final int getShipLength(ShipType shipType) {
    switch (shipType) {
      
      case CRUISER:
        return 4;
        
      case DESTROYER:
        return 3;
    
      case FRIGATE:
        return 2;  
        
      case SUBMARINE:
        return 1;
        
        

      default:
        return 0;
    }
  }
  
}