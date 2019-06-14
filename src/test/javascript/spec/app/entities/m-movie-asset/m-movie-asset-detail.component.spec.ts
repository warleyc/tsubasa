/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMovieAssetDetailComponent } from 'app/entities/m-movie-asset/m-movie-asset-detail.component';
import { MMovieAsset } from 'app/shared/model/m-movie-asset.model';

describe('Component Tests', () => {
  describe('MMovieAsset Management Detail Component', () => {
    let comp: MMovieAssetDetailComponent;
    let fixture: ComponentFixture<MMovieAssetDetailComponent>;
    const route = ({ data: of({ mMovieAsset: new MMovieAsset(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMovieAssetDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMovieAssetDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMovieAssetDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMovieAsset).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
