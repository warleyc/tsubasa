/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCommonBannerDetailComponent } from 'app/entities/m-common-banner/m-common-banner-detail.component';
import { MCommonBanner } from 'app/shared/model/m-common-banner.model';

describe('Component Tests', () => {
  describe('MCommonBanner Management Detail Component', () => {
    let comp: MCommonBannerDetailComponent;
    let fixture: ComponentFixture<MCommonBannerDetailComponent>;
    const route = ({ data: of({ mCommonBanner: new MCommonBanner(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCommonBannerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCommonBannerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCommonBannerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCommonBanner).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
