/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MStampDetailComponent } from 'app/entities/m-stamp/m-stamp-detail.component';
import { MStamp } from 'app/shared/model/m-stamp.model';

describe('Component Tests', () => {
  describe('MStamp Management Detail Component', () => {
    let comp: MStampDetailComponent;
    let fixture: ComponentFixture<MStampDetailComponent>;
    const route = ({ data: of({ mStamp: new MStamp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MStampDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MStampDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MStampDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mStamp).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
