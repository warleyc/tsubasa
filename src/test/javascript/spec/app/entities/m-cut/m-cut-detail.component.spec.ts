/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCutDetailComponent } from 'app/entities/m-cut/m-cut-detail.component';
import { MCut } from 'app/shared/model/m-cut.model';

describe('Component Tests', () => {
  describe('MCut Management Detail Component', () => {
    let comp: MCutDetailComponent;
    let fixture: ComponentFixture<MCutDetailComponent>;
    const route = ({ data: of({ mCut: new MCut(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCutDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCutDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCutDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCut).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
