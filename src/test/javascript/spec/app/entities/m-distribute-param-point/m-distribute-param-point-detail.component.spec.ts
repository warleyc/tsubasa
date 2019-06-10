/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDistributeParamPointDetailComponent } from 'app/entities/m-distribute-param-point/m-distribute-param-point-detail.component';
import { MDistributeParamPoint } from 'app/shared/model/m-distribute-param-point.model';

describe('Component Tests', () => {
  describe('MDistributeParamPoint Management Detail Component', () => {
    let comp: MDistributeParamPointDetailComponent;
    let fixture: ComponentFixture<MDistributeParamPointDetailComponent>;
    const route = ({ data: of({ mDistributeParamPoint: new MDistributeParamPoint(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDistributeParamPointDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDistributeParamPointDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDistributeParamPointDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDistributeParamPoint).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
