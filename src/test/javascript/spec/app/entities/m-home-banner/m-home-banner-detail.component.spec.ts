/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MHomeBannerDetailComponent } from 'app/entities/m-home-banner/m-home-banner-detail.component';
import { MHomeBanner } from 'app/shared/model/m-home-banner.model';

describe('Component Tests', () => {
  describe('MHomeBanner Management Detail Component', () => {
    let comp: MHomeBannerDetailComponent;
    let fixture: ComponentFixture<MHomeBannerDetailComponent>;
    const route = ({ data: of({ mHomeBanner: new MHomeBanner(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MHomeBannerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MHomeBannerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MHomeBannerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mHomeBanner).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
