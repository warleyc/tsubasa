/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCardThumbnailAssetsDetailComponent } from 'app/entities/m-card-thumbnail-assets/m-card-thumbnail-assets-detail.component';
import { MCardThumbnailAssets } from 'app/shared/model/m-card-thumbnail-assets.model';

describe('Component Tests', () => {
  describe('MCardThumbnailAssets Management Detail Component', () => {
    let comp: MCardThumbnailAssetsDetailComponent;
    let fixture: ComponentFixture<MCardThumbnailAssetsDetailComponent>;
    const route = ({ data: of({ mCardThumbnailAssets: new MCardThumbnailAssets(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardThumbnailAssetsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCardThumbnailAssetsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCardThumbnailAssetsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCardThumbnailAssets).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
