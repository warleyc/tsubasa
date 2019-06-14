/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTipsDetailComponent } from 'app/entities/m-tips/m-tips-detail.component';
import { MTips } from 'app/shared/model/m-tips.model';

describe('Component Tests', () => {
  describe('MTips Management Detail Component', () => {
    let comp: MTipsDetailComponent;
    let fixture: ComponentFixture<MTipsDetailComponent>;
    const route = ({ data: of({ mTips: new MTips(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTipsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTipsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTipsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTips).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
