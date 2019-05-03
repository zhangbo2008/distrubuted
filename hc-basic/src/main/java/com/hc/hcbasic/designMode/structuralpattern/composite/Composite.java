package com.hc.hcbasic.designMode.structuralpattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-01 21:14
 */
public class Composite extends Component {

    // 用来保存节点的子节点
    List<Component> list = new ArrayList<>();

    @Override
    public void add(Component c) {
        list.add(c);
    }

    // 删除节点 删除部件
    @Override
    public void remove(Component c) {
        list.remove(c);
    }

    // 遍历子节点
    @Override
    public void eachChild() {
        System.out.println(name + "执行了");
        for (Component c : list) {
            c.eachChild();
        }
    }
}
