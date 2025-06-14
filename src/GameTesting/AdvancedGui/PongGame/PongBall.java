package GameTesting.AdvancedGui.PongGame;

import GameTesting.AdvancedGui.Console.Debug;
import GameTesting.AdvancedGui.PongGame.Models.*;
import GameTesting.AdvancedGui.Components.Drawable;
import GameTesting.AdvancedGui.Components.GameComponent;
import GameTesting.AdvancedGui.PongGame.HelperClasses.MovementHelper;
import GameTesting.AdvancedGui.PongGame.HelperClasses.RenderHelper;
import GameTesting.AdvancedGui.PongGame.Models.Sprites.SpriteBase;

import java.util.Objects;

public class PongBall extends Drawable implements GameComponent {

    public enum Side {
        top,
        right,
        bottom,
        left
    }

    private int moveDir = 45;
    private int speed = 3;
    private int rotationSpeed = 3;

    private SpriteBase ballSprite;


    public PongBall(int initX, int initY, Rectangle bounds) {
        height = 16;
        width = height;
        position = new Point(initX, initY);
        ballSprite = new SpriteBase("Ball.png");
    }

    @Override
    public void render() {
        //RenderHelper.renderColor(this, 0xDDFFDD);
        RenderHelper.renderSprite(ballSprite, position.getX(), position.getY());
    }

    @Override
    public void update() {
        Point updatedPos = MovementHelper.getPointFromDirAndSpeed(moveDir, speed, position);
        Rectangle predictedArea = new Rectangle(updatedPos, width, height);

        CollisionArea collidingWall = Pong.getWalls().stream()
                .filter(area -> CollisionHelper.isOverlapping(predictedArea, area.getArea()))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(collidingWall)) {
            position = updatedPos;
        } else {
            updateAngleOnCollision(predictedArea, collidingWall);
        }

    }

    private void updateAngleOnCollision(Rectangle predictedArea, CollisionArea collidingWall) {
        Side side = getCollisionSide(collidingWall, predictedArea);
        if (side.equals(Side.bottom)) {
            if (moveDir < 90) {
                moveDir = 360 - moveDir;
            }
            else if (moveDir > 90) {
                moveDir = 180 + (180 - moveDir);
            } else {
                moveDir += 180;
            }
        } else if (side.equals(Side.right)) {
            if (moveDir == 0) moveDir += 180;
            if (moveDir > 270) {
                moveDir = 360 - moveDir + 180;
            }
            if (moveDir < 90) {
                moveDir = 90 - moveDir + 90;
            }
        } else if (side.equals(Side.left)) {
            if (moveDir > 180) {
                moveDir = 270 - moveDir + 270;
            }
            if (moveDir < 180) {
                moveDir = 90 - (moveDir - 90);
            }
            if (moveDir == 180) {
                moveDir += 180;
            }
        } else if (side.equals(Side.top)) {
            if (moveDir < 270) {
                moveDir = 180 - (moveDir - 180);
            }
            if (moveDir > 270) {
                moveDir = 360 - moveDir;
            }
            if (moveDir == 270) {
                moveDir -= 180;
            }
        }
        update();
    }

    public Side getCollisionSide(CollisionArea collidingWall, Rectangle predictedArea) {
        Point topLeft, topRight, botLeft, botRight;
        topLeft = predictedArea.getPoint();
        topRight = new Point(topLeft.getX() + predictedArea.getWidth(), topLeft.getY());
        botLeft = new Point(topLeft.getX(), topLeft.getY() + predictedArea.getHeight());
        botRight = new Point(topRight.getX(), botLeft.getY());

        boolean topL = CollisionHelper.pointIsInArea(collidingWall.getArea(), topLeft);
        boolean topR = CollisionHelper.pointIsInArea(collidingWall.getArea(), topRight);
        boolean botL = CollisionHelper.pointIsInArea(collidingWall.getArea(), botLeft);
        boolean botR = CollisionHelper.pointIsInArea(collidingWall.getArea(), botRight);

        if (topL && topR) {
            return Side.top;
        }
        if (topR && botR) {
            return Side.right;
        }
        if (topL && botL) {
            return Side.left;
        }
        if (botL && botR) {
            return Side.bottom;
        }

        Point corner =
                collidingWall.getCollisionPoint(predictedArea);

        if (topL) {
            int xDiff = Math.abs(topLeft.getX() - corner.getX());
            int yDiff = Math.abs(topLeft.getY() - corner.getY());
            if (xDiff >= yDiff) return Side.top;
            else return Side.left;
        }
        if (topR) {
            int xDiff = Math.abs(topRight.getX() - corner.getX());
            int yDiff = Math.abs(topRight.getY() - corner.getY());
            if (xDiff >= yDiff) return Side.top;
            else return Side.right;
        }
        if (botL) {
            int xDiff = Math.abs(botLeft.getX() - corner.getX());
            int yDiff = Math.abs(botLeft.getY() - corner.getY());
            if (xDiff >= yDiff) return Side.bottom;
            else return Side.left;
        }
        if (botR) {
            int xDiff = Math.abs(botRight.getX() - corner.getX());
            int yDiff = Math.abs(botRight.getY() - corner.getY());
            if (xDiff >= yDiff) return Side.bottom;
            else return Side.right;
        }


        return null;
    }

    public Point getCenter() {
        int x = position.getX() + (width / 2);
        int  y = position.getY() + (height / 2);
        return new Point(x,  y);
    }

    public void adjustDirFromGravity(double directionToGravity, double distance) {
        int diffSub = 0;
        int diffAdd = 0;

        for (int add = (int)directionToGravity; !withinRange(moveDir, add, rotationSpeed); add++) {
            if (add > 360) add -= 360;
            diffAdd++;
        }
        for (int sub = (int)directionToGravity; !withinRange(moveDir, sub, rotationSpeed); sub--) {
            if (sub < 0) sub += 360;
            diffSub++;
        }

        if (diffSub < diffAdd) {
            moveDir += rotationSpeed;
            if (moveDir >= 360) moveDir -= 360;
        } else {
            moveDir -= rotationSpeed;
            if (moveDir <= 0) moveDir += 360;
        }

    }

    private boolean withinRange(int baseValue, int checkedValue, int range) {
        return Math.abs(baseValue - checkedValue) <= range;
    }

    @Override
    public String toString() {
        return "PongBall{" +
                "position=" + position +
                ", moveDir=" + moveDir +
                ", speed=" + speed +
                '}';
    }
}
