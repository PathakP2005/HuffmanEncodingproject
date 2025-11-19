package com.company;

class ADD {
    int data;
    ADD next;
}

public class newInsertionDSA {
    //display linked list
    static void display(ADD head) {
        ADD ptr = head;
        while (ptr != null) {
            System.out.print(ptr.data + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    // Insert at the beginning
    static ADD insertAtFirst(ADD head, int data) {
        ADD ptr = new ADD();
        ptr.data = data;
        ptr.next = head;
        return ptr;  // new head
    }

    // Insert in between
    static ADD insertAtIndex(ADD head, int data, int index) {
        ADD ptr = new ADD();
        ptr.data = data;

        if (index == 0) {
            ptr.next = head;
            return ptr;
        }

        ADD p = head;
        int i = 0;
        while (p != null && i < index - 1) {
            p = p.next;
            i++;
        }

        if (p != null) {
            ptr.next = p.next;
            p.next = ptr;
        } else {
            System.out.println("Index out of range!");
        }

        return head;
    }

    // Insert at the End

    static ADD insertAtEnd(ADD head, int data) {
        ADD ptr = new ADD();
        ptr.data = data;
        ptr.next = null;

        if (head == null) {
            return ptr;
        }
        ADD temp  = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = ptr;
        return head;
    }
    // Insert after a node
    static void insertafterADD(ADD prevAdd, int data){
    if(prevAdd == null){
        System.out.println("Previous add cannot ne null!");
        return;
    }
        ADD ptr = new ADD() ;
    ptr.data = data;
    ptr.next = prevAdd.next;
    prevAdd.next = ptr;
}
    public static void main(String[] args) {
        ADD head = new ADD();
        ADD second = new ADD();
        ADD third = new ADD();
        ADD fourth = new ADD();

        // Link nodes
        head.data = 7;
        head.next = second;

        second.data = 11;
        second.next = third;

        third.data = 41;
        third.next = fourth;

        fourth.data = 66;
        fourth.next = null;


        //display original linked list
        System.out.println("Original Linked List:");
        display(head);

        //Insert at beginning
        head = insertAtFirst(head, 56);
        System.out.println("After inserting 56 at beginning:");
        display(head);

        //Insert in between
        head = insertAtIndex(head, 34,3);
        System.out.println("\n After inserting 34 at index 3");
        display(head);

        //Insert at end
        head = insertAtEnd(head,100 );
        System.out.println("\n After inserting 100 at the end:");
        display(head);

        //Insert after a specific node (say after 'third' node)
        insertafterADD(third, 45);
        System.out.println("\n After inserting 45 at the third node:");
        display(head);
    }
}

