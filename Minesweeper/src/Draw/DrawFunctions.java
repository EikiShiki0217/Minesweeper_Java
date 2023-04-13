package Draw;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawFunctions {

    public void drawConvexBorder(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.decode("#919191"));

        g.fillPolygon(new int[]{x, x + width, x + width - 3, x + 3}, new int[]{y, y, y + 3, y + 3}, 4);
        g.fillPolygon(new int[]{x, x + 3, x + 3, x}, new int[]{y, y + 3, y + height - 3, y + height}, 4);
        
        g.setColor(Color.decode("#ffffff"));

        g.fillPolygon(new int[]{x, x + width, x + width - 3, x + 3}, new int[]{y + height, y + height, y + height - 3, y + height - 3}, 4);
        g.fillPolygon(new int[]{x + width, x + width, x + width - 3, x + width - 3}, new int[]{y, y + height, y + height - 3, y + 3}, 4);
    }

    public void drawConcaveBorder(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.decode("#ffffff"));

        g.fillPolygon(new int[]{x, x + width, x + width - 3, x + 3}, new int[]{y, y, y + 3, y + 3}, 4);
        g.fillPolygon(new int[]{x, x + 3, x + 3, x}, new int[]{y, y + 3, y + height - 3, y + height}, 4);
        
        g.setColor(Color.decode("#919191"));

        g.fillPolygon(new int[]{x, x + width, x + width - 3, x + 3}, new int[]{y + height, y + height, y + height - 3, y + height - 3}, 4);
        g.fillPolygon(new int[]{x + width, x + width, x + width - 3, x + width - 3}, new int[]{y, y + height, y + height - 3, y + 3}, 4);
    }

    public void drawCells(Graphics g, int[][] currentCells) {
        for (int i = 0; i < currentCells.length; i++) {
            for (int j = 0; j < currentCells[0].length; j++) {
                if (currentCells[i][j] == -1) {
                    drawConcaveBorder(g, j * 30 + 10, i * 30 + 61, 30, 30);
                } else if (currentCells[i][j] == -2) {
                    drawFlag(g, i, j);
                } else if (currentCells[i][j] == -3) {
                    drawNumbers(g, i, j, currentCells[i][j]);
                } else if (currentCells[i][j] == 9|| currentCells[i][j] == 10) {
                    drawBomb(g, i, j, currentCells[i][j]);
                } else {
                    drawNumbers(g, i, j, currentCells[i][j]);
                }
            }
        }
    }

    public void drawFlag(Graphics g, int i, int j){
        try {
            BufferedImage img = ImageIO.read(new File("src\\Image\\Flag.png"));
            g.drawImage(img, 10 + j * 30, 61 + i * 30, 30, 30, null);
        } catch (IOException e) {
        }
    }

    public void drawNumbers(Graphics g, int i, int j, int index) {
        Color[] colors = {Color.decode("#0000ff"), Color.decode("#007b00"),
                    Color.decode("#ff0000"), Color.decode("#00007b"),
                    Color.decode("#7b0000"), Color.decode("#007b7b"),
                    Color.decode("#000000"), Color.decode("#7b7b7b")};
        int x = 10 + j * 30;
        int y = 61 + i * 30;

        g.setColor(Color.decode("#cbcbcb"));
        g.fillRect(x , y, 30, 30);

        g.setColor(Color.decode("#919191"));
        g.fillPolygon(new int[]{x, x + 30, x + 30, x + 1}, new int[]{y, y, y + 1, y + 1}, 4);
        g.fillPolygon(new int[]{x, x + 1, x + 1, x}, new int[]{y, y + 1, y + 30, y + 30}, 4);

        g.setColor(Color.decode("#cbcbcb"));
        g.fillRect(x + 1, y + 1, 29, 29);

        if(index > 0) {
            g.setColor(colors[index - 1]);
            g.setFont(new Font("Times New Roman", Font.BOLD, 27));
            g.drawString("" + index, x + 9, y + 24);
        }
    }

    public void drawBomb(Graphics g, int i, int j, int index) {
        try {
            String imgName = null;
            if (index == 9) {
                imgName = "src\\Image\\Mine.png";
            } else if (index == 10) {
                imgName = "src\\Image\\MineRed.png";
            }
            BufferedImage img =  ImageIO.read(new File(imgName));
            g.drawImage(img, 10 + j * 30, 61 + i * 30, 30, 30, null);
        } catch (IOException e) {
        }
    }

    public void drawClockBG(Graphics g, int width, int height) {
        g.setColor(Color.BLACK);

        g.fillRect(14, 14, 54, 33);
        g.fillRect(width - 68, 14, 54, 33);
    }

    public void drawClock(Graphics g, int width, int height, int time) {
        int isEnd = 1;
        while (isEnd < 101) {
            char[] decode = decoder((time / isEnd) % 10).toCharArray();
            for (int i = 0; i < decode.length; i++) {
                drawRedNumbers(g, width, height, i, Character.getNumericValue(decode[i]), isEnd, true);
            }
            isEnd *= 10;
        }
    }

    public void drawFlagNumber(Graphics g, int width, int height, int flag) {
        int isEnd = 1;
        char[] negative = decoder(0).toCharArray();
        if (flag < 0) {
            flag = flag * -1;
            for (int i = 0; i < negative.length; i++) {
                if (negative[i] == '0') {
                    negative[i] = '1';
                } else {
                    negative[i] = '0';
                }
            }
        }
        for (int i = 0; i < negative.length; i++) {
            drawRedNumbers(g, width, height, i, Character.getNumericValue(negative[i]), 100, false);
        }
        while (isEnd < 11) {
            char[] decode = decoder((flag / isEnd) % 10).toCharArray();
            for (int i = 0; i < decode.length; i++) {
                drawRedNumbers(g, width, height, i, Character.getNumericValue(decode[i]), isEnd, false);
            }
            isEnd *= 10;
        }
    }
    
    public void drawRedNumbers(Graphics g, int width, int height, int bit, int decode, int Oron, Boolean time) {
        int x = width - 69;
        int y = 15;
        if (time) {
            if (Oron == 1) {
                x = width - 31;
            } else if (Oron == 10) {
                x = width - 49;
            } else if (Oron == 100) {
                x = width - 67;
            }
        } else {
            if (Oron == 1) {
                x = 51;
            } else if (Oron == 10) {
                x = 33;
            } else if (Oron == 100) {
                x = 15;
            }
        }
        if (decode == 1) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.decode("#440000"));
        }
        if (bit == 0) {
            g.fillPolygon(new int[]{x + 1, x + 15, x + 11, x + 5}, new int[]{y, y, y + 4, y + 4}, 4);
        } else if (bit == 1){
            g.fillPolygon(new int[]{x + 16, x + 16, x + 12, x + 12}, new int[]{y + 1, y + 15, y + 11, y + 5}, 4);
        } else if (bit == 2){
            g.fillPolygon(new int[]{x + 16, x + 16, x + 12, x + 12}, new int[]{y + 16, y + 30, y + 26, y + 20}, 4);
        } else if (bit == 3){
            g.fillPolygon(new int[]{x + 1, x + 15, x + 11, x + 5}, new int[]{y + 31, y + 31, y + 27, y + 27}, 4);
        } else if (bit == 4){
            g.fillPolygon(new int[]{x, x, x + 4, x + 4}, new int[]{y + 16, y + 30, y + 26, y + 20}, 4);
        } else if (bit == 5){
            g.fillPolygon(new int[]{x, x, x + 4, x + 4}, new int[]{y + 1, y + 15, y + 11, y + 5}, 4);
        } else if (bit == 6){
            g.fillPolygon(new int[]{x + 1, x + 3, x + 13, x + 15, x + 15, x + 13, x + 3, x + 1}, new int[]{y + 15, y + 13, y + 13, y + 15, y + 16, y + 18, y + 18, y + 16}, 8);
        }
    }

    public String decoder(int number) {
        String decode = "0000000";
        switch (number) {
            case 0:
                decode = "1111110";
                break;
            case 1:
                decode = "0110000";
                break;
            case 2:
                decode = "1101101";
                break;
            case 3:
                decode = "1111001";
                break;
            case 4:
                decode = "0110011";
                break;
            case 5:
                decode = "1011011";
                break;
            case 6:
                decode = "1011111";
                break;
            case 7:
                decode = "1110000";
                break;
            case 8:
                decode = "1111111";
                break;
            case 9:
                decode = "1111011";
                break;
            default:
                break;
        }
        return decode;
    }

    public void drawSmile(Graphics g, int width, Boolean press, Boolean isWin, Boolean isLose, Boolean onSmile) {
        try {
            String imageName = null;
            if (onSmile) {
                imageName = "src\\Image\\SmilePressStart.png";
            } else {
                if (isWin) {
                    imageName = "src\\Image\\SmileWin.png";
                } else if (isLose) {
                    imageName = "src\\Image\\SmileLose.png";
                } else {
                    if (press) {
                        imageName = "src\\Image\\SmilePress.png";
                    } else {
                        imageName = "src\\Image\\Smile.png";
                    }
                }
            }
            BufferedImage img = ImageIO.read(new File(imageName));
            g.drawImage(img, (width - 36) / 2, 12, 36, 36, null);
        } catch (IOException e) {
        }
    }

    public void drawTitle(Graphics g, int width) {
        try {
            String imageName = "Minesweeper\\src\\Image\\Name.png";
            BufferedImage img = ImageIO.read(new File(imageName));
            g.drawImage(img, (width - 362) / 2, 13, 362, 35, null);
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
