�U�Ώ? c���MJG��y�
]������6lۖ�=����N��q$C������I4��ܾ5%�p�gi'
�)X\���h)j%�DI��P��]���co��K��A���f���٩'$���P�O�V��1�X�
~���m��f��}                                                                                                                                                                                                                                                                                                                                                                               boolean pressed;
            @Override
            public void mousePressed(MouseEvent e){
                pressed = true;
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if(pressed){
                    if(SwingUtilities.isRightMouseButton(e)){
                        setIcon(null);
                    }
                }
            }
        });


    }
    public void actionPerformed(ActionEvent e){
        value ++;
        value%=3;
        switch(value){
            case 0:
                setIcon(null);
            break;
            case 1:
                setIcon(X);
                break;
            case 2:
                setIcon(O);
                break;
        }

    }

}
*/
