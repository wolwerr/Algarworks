import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

import { IObjetctCurso, ICurso } from '../../../models/curso';
import { CursoService } from '../../../services/curso.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-table-curso',
  templateUrl: './table-curso.component.html',
  styleUrls: ['./table-curso.component.scss'],
})
export class TableCursoComponent implements OnInit {
  student: ICurso[]

  displayedColumns = [
    'Nome',

  ];
  dataSource = new MatTableDataSource<ICurso>();

  constructor(private cursoService: CursoService,
    private router: Router
    ) { }

  ngOnInit() {
    this.getStudentsInformation()
  }

  getStudentsInformation() {
    this.cursoService.getAllCursos().subscribe((res: IObjetctCurso) => {
      console.log(res)
      this.dataSource.data = res.content
    });
  }

  @ViewChild('paginator') paginator: MatPaginator

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator
  }

  cadastrar(){
    this.router.navigateByUrl('/main/cadastrocurso');
  }

}
