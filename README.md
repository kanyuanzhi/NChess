NChess
===

本demo在终端里实现任意N子棋，效果如下：
https://github.com/kanyuanzhi/TicTacToe/raw/master/images/sketch.png

下法
---
根据提示直接输入棋子坐标，例如23，44分别对应棋盘第二行第三列，第四行第四列。

使用
---
以五子棋为例，棋盘大小为10×10
```java
NChess n = new NChess();
n.init(10,5);
n.begin();
```
