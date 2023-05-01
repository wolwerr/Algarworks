import {Component, OnInit} from '@angular/core';
import {ICurso} from "../../../models/curso";
import {MatTableDataSource} from "@angular/material/table";
import {ModelSelectEnum} from "../../../models/model.select.enum";
import {IProduto} from "../../../models/produto";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {OfertaService} from "../../../services/oferta.service";
import {IOferta} from "../../../models/oferta";

@Component({
  selector: 'app-form-oferta',
  templateUrl: './form-oferta.component.html',
  styleUrls: ['./form-oferta.component.scss']
})
export class FormOfertaComponent implements OnInit {
  dataSource = new MatTableDataSource<ICurso>([]);

  modelCourse = ModelSelectEnum.courses;
  modelProduct = ModelSelectEnum.products;

  isEdit: boolean = false;

  code: string ;
  internalName: string = '';
  price: string = '';
  salesStartingAt: string = '';
  salesEndingAt: string = '';
  supportDurationInDays: string = '';
  activeSelected: boolean;
  produtoSelected: IProduto | null;
  cursoList: Array<ICurso> = [];
  displayedColumns: string[] = ['Nome'];
  ofertaService: OfertaService;


  constructor(private activedRoute: ActivatedRoute, private http: HttpClient, private router: Router) {
    this.ofertaService = new OfertaService(this.http);
  }

  ngOnInit(): void {
    if (this.activedRoute.snapshot.paramMap.get('code')) {
      this.isEdit = true;
      this.code = this.activedRoute.snapshot.paramMap.get('code')!;

      this.ofertaService.findOfertaByCode(this.code).subscribe({
        next: (n) => {
          this.code = n.code
          this.internalName = n.internalName;
          this.price = n.price;
          this.activeSelected = n.active;
          this.salesStartingAt = n.salesStartingAt;
          this.salesEndingAt = n.salesEndingAt;
          this.produtoSelected = n.product;
          this.cursoList = n.deliverables;
          this.dataSource.data = this.cursoList;
          this.supportDurationInDays = n.supportDurationInDays
        }
      })
    }
  }

  submit() {
    let body: IOferta = {
      code: this.code,
      internalName: this.internalName,
      price: this.price,
      active: this.activeSelected,
      product: this.produtoSelected!,
      salesStartingAt: this.salesStartingAt,
      salesEndingAt: this.salesEndingAt,
      deliverables: this.cursoList,
      supportDurationInDays: this.supportDurationInDays
    }
    if(this.cursoList == null || this.produtoSelected == null){
      alert('Faltou algum campo a ser preenchido');
    }else{
    if (this.isEdit) {
      this.ofertaService.update(body).subscribe({
        next: (n) => {
          alert('Cadastro Atualizado!')
          this.router.navigateByUrl('main/oferta')
        },
        error: (e) => {
          alert('Faltou algum campo a ser preenchido');
        }
      });
    } else {
      this.ofertaService.register(body).subscribe({
        next: (n) => {
          alert('cadastrado com Sucesso!')
          this.code = '';
          this.internalName = '';
          this.price = '';
          this.activeSelected = false;
          this.salesStartingAt = '';
          this.salesEndingAt = '';
          this.supportDurationInDays = '';
          this.cursoList = [];
          this.produtoSelected = null;
          this.dataSource.data = [];
          if(!confirm('gostaria de cadastrar outro?')) {
            this.router.navigateByUrl('main/oferta')
          }
        },
        error: (e) => {
          alert('Faltou algum campo a ser preenchido');
        }
      });
    }
  }}

  produtoChanged(produto: IProduto): void {
    this.produtoSelected = produto;
  }

  removeItem(i: number) {
    this.cursoList.splice(i, 1);
    this.dataSource.data = this.cursoList;
  }


  cursoChanged(curso: ICurso): void {
    this.cursoList.push(curso);
    this.dataSource.data = this.cursoList;
  }

  removeProduto() {
    this.produtoSelected = null;
  }

  delete() {
    if(confirm('Você está prestes a excluir a classe, esta ação não pode ser desfeita!')) {
      this.ofertaService.delete(this.code!).subscribe({
        next: (n) => {
          alert('Oferta excluída com sucesso!');
          this.router.navigateByUrl('/main/oferta')
        }
      })
    }
  }

  voltar() {
    this.router.navigateByUrl('/main/oferta');
  }

}
