package com.dailycodework.buynowdotcom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    //    NaturalId は、現実世界でも意味を持つドメインモデルの一意の識別子
//    主キーとして適切でない場合でも（通常は代理キーが推奨されます）、Hibernate にその ID を伝えることは有用
    @NaturalId
    private String email;
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    //    fetch 戦略は EAGER 戦略が基本値
//    EAGER : 対象テーブル取得時に関連テーブルも取得するSQLも実行する
//    LAZY : 関連テーブルのフィールドを参照した時に、関連テーブルを取得するSQLを実行する
//    EAGERだと、関連テーブル不要のときも関連テーブル取得用SQLが実行されるため、EAGERは設定しないのがベスト
    @ManyToMany(fetch = FetchType.EAGER, cascade =
//            CascadeType.DETACH : 親エンティティがデタッチされた際に、関連する子エンティティも自動的にデタッチされる
//            デタッチとは、エンティティがPersistence Contextから切り離され、データベースの管理下から外れることを指す
//            CascadeType.MERGE : は、親エンティティの状態を更新した際に、その子エンティティの状態も同様にデータベースで更新されるようにする
//            CascadeType.PERSIST : は、親エンティティを保存する際に、関連する子エンティティも自動的にデータベースに保存されるようにする
//            CascadeType.REFRESH : は、親エンティティがデータベースから再読み込みされた際に、その子エンティティも自動的に再読み込みされる
            {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_roles",
//          joinColumns属性 : 所有者側エンティティのプライマリテーブルを参照する，結合表の外部キーカラムを指定する属性
//          name属性には結合表の外部キーカラム名を指定
//          referencedColumnName属性には所有者側の参照カラム名を指定
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"),
//          inverseJoinColumns属性 : 被所有者側エンティティのプライマリテーブルを参照する，結合表の外部キーカラムを指定する属性
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    private Collection<Role> roles = new HashSet<>();
}
