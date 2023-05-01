import { Component, OnInit } from '@angular/core';
import {IOferta} from "../../../models/oferta";
import {MatTableDataSource} from "@angular/material/table";
import {ICurso} from "../../../models/curso";
import {OfertaService} from "../../../services/oferta.service";
import {HttpClient} from "@angular/common/http";
import {PageEvent} from "@angular/material/paginator";
import {animate, state, style, transition, trigger} from '@angular/animations';
import { Router } from '@angular/router';



@Component({
  selector: 'app-table-oferta',
  templateUrl: './table-oferta.component.html',
  styleUrls: ['./table-oferta.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ])]
})
export class TableOfertaComponent implements OnInit {

  ofertaDataSource: MatTableDataSource<IOferta> = new MatTableDataSource<IOferta>([]);
  cursoDataSource: MatTableDataSource<ICurso> = new MatTableDataSource<ICurso>([]);
  code: string | '' = '';
  offers: IOferta[];
  
  ofertaDisplayedColumns: string[] = ['code', 'nome', 'internalName', 'active', 'salesStartingAt', 'supportDurationInDays'];
  cursoDisplayedColumns: string[] = ['nome', 'supportDurationInDays'];
  
  
  
  columnsToDisplayWithExpand = [...this.ofertaDisplayedColumns, 'expand'];

  expandedElement: any;
  isLoadingRow: boolean = false;
  private ofertaService: OfertaService;

  constructor(private http: HttpClient, private router: Router, ) {
    this.ofertaService = new OfertaService(this.http);
    this.ofertaService.findAll().subscribe({
      next: (n) => {
        this.ofertaDataSource.data = n.content
      }
    });    
  }

  ngOnInit(): void {
   // this.cursoDisplayedColumns.push('supportDurationInDays');

  }

  rowClicked(row: IOferta) {
    this.code = row.code!;
    this.expandedElement = this.expandedElement === row ? null : row;
    console.log(row)
    this.cursoDataSource.data = row.deliverables;

  }

  setPage($event: PageEvent) {

  }

  editClick() {
    this.router.navigateByUrl('/main/cadastrooferta/' + this.code)
  }

  cursoClicked(row: any) {
    console.log(row);
  }

  excluir() {
    if(confirm('Você está prestes a excluir a classe, esta ação não pode ser desfeita!')) {
      this.ofertaService.delete(this.code!).subscribe({
        next: (n) => {
          alert('Oferta excluída com sucesso!');
        }
      })
      window.location.reload();
    }
  }

}
