/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCardIllustAssetsDetailComponent } from 'app/entities/m-card-illust-assets/m-card-illust-assets-detail.component';
import { MCardIllustAssets } from 'app/shared/model/m-card-illust-assets.model';

describe('Component Tests', () => {
  describe('MCardIllustAssets Management Detail Component', () => {
    let comp: MCardIllustAssetsDetailComponent;
    let fixture: ComponentFixture<MCardIllustAssetsDetailComponent>;
    const route = ({ data: of({ mCardIllustAssets: new MCardIllustAssets(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardIllustAssetsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCardIllustAssetsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCardIllustAssetsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCardIllustAssets).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
