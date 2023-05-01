import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';


import { IObjetctProduto, IProduto } from '../../../models/produto';
import { ProdutoService } from 'src/app/services/produto.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-table-produto',
  templateUrl: './table-produto.component.html',
  styleUrls: ['./table-produto.component.scss']
})
export class TableProdutoComponent implements OnInit {
  produto: IProduto[]

  displayedColumns = [
    'Nome',
    'Status'
  ];
  dataSource = new MatTableDataSource<IProduto>();

  constructor(private produtoService: ProdutoService,
    private router: Router
    ) {}

  ngOnInit() {
    this.getProdutoInformation()
  }

  getProdutoInformation() {
    this.produtoService.getAllProdutos().subscribe((res: IObjetctProduto) => {
      console.log(res)
      this.dataSource.data = res.content
    });
  }

  @ViewChild('paginator') paginator: MatPaginator

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator
  }

  cadastrar(){
    this.router.navigateByUrl('/main/cadastroproduto');
  }

}

