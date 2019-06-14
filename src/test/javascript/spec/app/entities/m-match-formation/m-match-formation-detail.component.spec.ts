/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMatchFormationDetailComponent } from 'app/entities/m-match-formation/m-match-formation-detail.component';
import { MMatchFormation } from 'app/shared/model/m-match-formation.model';

describe('Component Tests', () => {
  describe('MMatchFormation Management Detail Component', () => {
    let comp: MMatchFormationDetailComponent;
    let fixture: ComponentFixture<MMatchFormationDetailComponent>;
    const route = ({ data: of({ mMatchFormation: new MMatchFormation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMatchFormationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMatchFormationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMatchFormationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMatchFormation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
