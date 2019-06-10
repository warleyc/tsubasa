/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAreaActionWeightDetailComponent } from 'app/entities/m-area-action-weight/m-area-action-weight-detail.component';
import { MAreaActionWeight } from 'app/shared/model/m-area-action-weight.model';

describe('Component Tests', () => {
  describe('MAreaActionWeight Management Detail Component', () => {
    let comp: MAreaActionWeightDetailComponent;
    let fixture: ComponentFixture<MAreaActionWeightDetailComponent>;
    const route = ({ data: of({ mAreaActionWeight: new MAreaActionWeight(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAreaActionWeightDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MAreaActionWeightDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MAreaActionWeightDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mAreaActionWeight).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
