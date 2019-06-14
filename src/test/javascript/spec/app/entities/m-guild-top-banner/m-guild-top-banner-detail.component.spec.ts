/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildTopBannerDetailComponent } from 'app/entities/m-guild-top-banner/m-guild-top-banner-detail.component';
import { MGuildTopBanner } from 'app/shared/model/m-guild-top-banner.model';

describe('Component Tests', () => {
  describe('MGuildTopBanner Management Detail Component', () => {
    let comp: MGuildTopBannerDetailComponent;
    let fixture: ComponentFixture<MGuildTopBannerDetailComponent>;
    const route = ({ data: of({ mGuildTopBanner: new MGuildTopBanner(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildTopBannerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGuildTopBannerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuildTopBannerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGuildTopBanner).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
