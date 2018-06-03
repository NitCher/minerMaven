package miner;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;

public class Hexagon extends StackPane {

    private boolean isOpen = false;
    private boolean hasBomb;
    private Text countBombsAround = new Text(" ");
    private Polyline hexagon;
    private Point2D origin;
    private Image image;
    private ImageView statusImage;
    public boolean check = false;
    private long bombAround;

    public Hexagon(){

    }
    public Hexagon(Point2D center, int size) {
        origin =center;
        hexagon = new Polyline(
                calc_hexagon(center, size, 0).getX(), calc_hexagon(center, size, 0).getY(),
                calc_hexagon(center, size, 1).getX(), calc_hexagon(center, size, 1).getY(),
                calc_hexagon(center, size, 2).getX(), calc_hexagon(center, size, 2).getY(),
                calc_hexagon(center, size, 3).getX(), calc_hexagon(center, size, 3).getY(),
                calc_hexagon(center, size, 4).getX(), calc_hexagon(center, size, 4).getY(),
                calc_hexagon(center, size, 5).getX(), calc_hexagon(center, size, 5).getY(),
                calc_hexagon(center, size, 0).getX(), calc_hexagon(center, size, 0).getY()
        );
        hexagon.setStroke(Color.RED);
        hexagon.setFill(Color.LIGHTGRAY);
        getChildren().add(hexagon);
        setTranslateX(center.getX());
        setTranslateY(center.getY());
        getChildren().add(countBombsAround);
        countBombsAround.setVisible(false);
    }

    public void setCountBombAround(long bombAround){
        this.bombAround = bombAround;
        this.countBombsAround.setText(String.valueOf(bombAround));
    }

    public int openCell() {
        if (!isOpen) {
          if (hasBomb) return -1;
            else {
              isOpen = true;
              countBombsAround.setVisible(true);
              hexagon.setFill(null);
              return 1;
             }
        }
        return 0;
    }

    private Point2D calc_hexagon(Point2D center, int size, int i){
        double pointX=center.getX()+size/2, pointY=center.getY()+size/2;
        double angle_def = 60* i+30;
        double angle = Math.PI / 180 * angle_def;
        pointX+=size*Math.cos(angle);
        pointY+=size*Math.sin(angle);
        return new Point2D(pointX,pointY);
    }

    public boolean isHasBomb() {
        return hasBomb;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void cellBang() {
        hexagon.setFill(null);
        if (image==null){ return;}
        if (!check) {
            ImageView bomb = new ImageView(image);
            getChildren().add(bomb);//
            bomb.setVisible(true);
            return;
        }else {
            statusImage = new ImageView(new Image("cancel_bomb.jpg"));
            getChildren().add(statusImage);
            statusImage.setVisible(true);
            check = false;
            return;
        }
    }

    public void setStatusImage(Image stat){
        if(stat!=null) {
            statusImage = new ImageView(stat);
            getChildren().add(statusImage);
            statusImage.setVisible(true);
            check = true;
        }else{
        try {
            statusImage.setVisible(false);
            check = false;
        }catch (NullPointerException e){
        }
        }
    }
public long getBombAround(){
        return bombAround;
}

public void setHasBomb(boolean active){
    hasBomb = active;
        image = new Image("bomb.jpg");
}
}