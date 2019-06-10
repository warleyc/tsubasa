/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MFormationDetailComponent } from 'app/entities/m-formation/m-formation-detail.component';
import { MFormation } from 'app/shared/model/m-formation.model';

describe('Component Tests', () => {
  describe('MFormation Management Detail Component', () => {
    let comp: MFormationDetailComponent;
    let fixture: ComponentFixture<MFormationDetailComponent>;
    const route = ({ data: of({ mFormation: new MFormation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MFormationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MFormationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MFormationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mFormation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
