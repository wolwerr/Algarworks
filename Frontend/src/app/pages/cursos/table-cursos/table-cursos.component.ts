import { CursosService } from './../../../services/cursos.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ICursos , IObjectCursos } from 'src/app/models/cursos';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-table-cursos',
  templateUrl: './table-cursos.component.html',
  styleUrls: ['./table-cursos.component.scss']
})
export class TableCursosComponent implements OnInit {

  cursos: ICursos[];

  displayedColumns: string[] = ['nome'];

  dataSource = new MatTableDataSource<ICursos>();


  constructor(private cursosService: CursosService) { }

  ngOnInit(): void {
    this.getCursosInformation()
  }

  getCursosInformation() {
    this.cursosService.findAll().subscribe((res: IObjectCursos) => {
      console.log(res)
      this.dataSource.data = res.content
    });
  }

  @ViewChild('paginator') paginator: MatPaginator

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator
  }

}
