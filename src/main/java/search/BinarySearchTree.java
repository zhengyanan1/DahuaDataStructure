package search;

/**
 * Created by Administrator on 2017/4/4.
 */

//注意：Java传参（值传递）

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class HelperTreeNode{
    TreeNode t;
    TreeNode parent;
    public HelperTreeNode(TreeNode t,TreeNode parent) {
        this.t = t;
        this.parent = parent;
    }
    void setChild(TreeNode child){
        if (parent.left == t)   parent.left = child;
        else                    parent.right = child;
    }
}
public class BinarySearchTree {

    
    /**
       @author zhengyanan
       @date 2017/4/4 @time 12:26
       @desc 递归查找二叉排序树中是否存在key
        parent指向root的双亲，其初始调用值为null.

        如果调用成功，p指向该数据元素结点，并返回true；
        否则，p指向查找路径上访问的最后一个结点,并返回false.
     */
    static TreeNode p;
    public static boolean SearchBST(TreeNode root,int key,TreeNode parent){
        if (root == null){
            p = parent;
            return false;
        }

        if (root.val == key){
            p = root;
            return true;
        }
        else if (root.val < key)    return SearchBST(root.right,key,root);
        else                        return SearchBST(root.left,key,root);
    }

    /**
       @author zhengyanan
       @date 2017/4/4 @time 13:52
       @desc 当二叉排序树中不存在关键字等于key的值时，插入key并返回true，否则返回false
    */
    public static boolean InsertBST(TreeNode root, int key){
        if (!SearchBST(root,key,null)){//查找不成功
            TreeNode s = new TreeNode(key);
//            if (p == null){//p 不可能 == null
//                root.val = s.val;   //插入s为新的根节点
//            }
            if (key < p.val)   p.left = s;
            else               p.right = s;

            return true;
        }
        return false;
    }

    /**
       @author zhengyanan
       @date 2017/4/4 @time 15:50
       @desc 如果二叉排序树中存在关键字等于key的数据元素，则删除该数据元素 结点
        parent,root结点的双亲结点，初始值为null.
    */
    public static boolean DeleteBST(TreeNode root,TreeNode parent,int key){
        if (root == null){
            return false;
        }

        if (key == root.val){
            System.out.println("***"+key);
            return Delete(new HelperTreeNode(root,parent));
        }
        else if (key < root.val)    return DeleteBST(root.left,root,key);
        else                        return DeleteBST(root.right,root,key);
    }

    /**
       @author zhengyanan
       @date 2017/4/4 @time 15:51
       @desc 从二叉排序树中删除该结点，并重接它的左或右子树
    */
    public static boolean Delete(HelperTreeNode helper){
        TreeNode t = helper.t;
        if (t.right == null)        {
            if (helper.parent == null){
                root = helper.t.right;
                return true;
            }
            helper.setChild(t.left);
        }
        else if (t.left == null)   {
            if (helper.parent == null){
                root = helper.t.right;
                return true;
            }
            helper.setChild(t.right);
        }
        else {
            //找到其中序遍历的前驱结点，用其val值替换当前结点，再把该前驱结点删除
            TreeNode q,s;
            q = t;
            s = q.left;
            while (s.right != null){
                q = s;s = s.right;
            }//s指向前驱结点
            t.val = s.val;System.out.println("---" + s.val);

            if (q == t) q.left = s.left;
            else        q.right = s.left;
        }
        return true;
    }


    /**
       @author zhengyanan
       @date 2017/4/4 @time 15:35
       @desc 按中序输出二叉树
    */
    public static void showBinaryTreeInMid(TreeNode root){
        if (root == null)   return;

        showBinaryTreeInMid(root.left);
        System.out.printf("%d ",root.val);
        showBinaryTreeInMid(root.right);
    }

    static TreeNode root = null;
    public static void main(String[] args){

        /**
           @author zhengyanan
           @date 2017/4/4 @time 15:34
           @desc 构建排序二叉树:只需调用插入函数即可(插入函数又会调用查找函数)
        */
        int[] a = new int[]{62,88,58,47,35,73,51,99,37,93};
        for (int i = 0; i < a.length; i++) {
            if (root == null){
                root = new TreeNode(a[i]);
            }
            else {
                InsertBST(root,a[i]);
            }
        }

        showBinaryTreeInMid(root);
        System.out.println();
        for (int i = 0; i < a.length; i++) {
            DeleteBST(root,null,a[i]);
            showBinaryTreeInMid(root);
            System.out.println();
            System.out.println(root);
        }

    }


}
