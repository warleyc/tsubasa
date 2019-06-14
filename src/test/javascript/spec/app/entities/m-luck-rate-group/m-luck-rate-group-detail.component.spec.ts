/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLuckRateGroupDetailComponent } from 'app/entities/m-luck-rate-group/m-luck-rate-group-detail.component';
import { MLuckRateGroup } from 'app/shared/model/m-luck-rate-group.model';

describe('Component Tests', () => {
  describe('MLuckRateGroup Management Detail Component', () => {
    let comp: MLuckRateGroupDetailComponent;
    let fixture: ComponentFixture<MLuckRateGroupDetailComponent>;
    const route = ({ data: of({ mLuckRateGroup: new MLuckRateGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLuckRateGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MLuckRateGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLuckRateGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mLuckRateGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
