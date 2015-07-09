		/*
		String stop="stop";
		bytes=stop.toCharArray();
		bits=new int[bytes.length*8];
		for (int c=0;c<(bytes.length);c++){
			for (int d=7;d>=0;d--){
				bits[(c*8)+d]=(bytes[c]&1);
				bytes[c]>>>=1;
			}
		}
		messloc=0;
		for (int i=0;i<8;i++){
			Color c=new Color(picture.getRGB(x,y));
			int red=c.getRed();
			int green=c.getGreen();
			int blue=c.getBlue();
			int redbit=(red&1);
			int greenbit=(green&1);
			int bluebit=(blue&1);
			int valuebit=bits[messloc];
			int dif=redbit-valuebit;
			int newred=red-dif;
			valuebit=bits[messloc+1];
			dif=greenbit-valuebit;
			int newgreen=green-dif;
			valuebit=bits[messloc+2];
			dif=bluebit-valuebit;
			int newblue=blue-dif;
			Color n=new Color(newred,newgreen,newblue);
			int newcolor=n.getRGB();
			picture.setRGB(x,y,newcolor);
			x++;
			messloc+=3;
		}
		*/