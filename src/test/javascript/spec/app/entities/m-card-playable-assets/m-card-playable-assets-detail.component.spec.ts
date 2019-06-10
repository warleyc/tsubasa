/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCardPlayableAssetsDetailComponent } from 'app/entities/m-card-playable-assets/m-card-playable-assets-detail.component';
import { MCardPlayableAssets } from 'app/shared/model/m-card-playable-assets.model';

describe('Component Tests', () => {
  describe('MCardPlayableAssets Management Detail Component', () => {
    let comp: MCardPlayableAssetsDetailComponent;
    let fixture: ComponentFixture<MCardPlayableAssetsDetailComponent>;
    const route = ({ data: of({ mCardPlayableAssets: new MCardPlayableAssets(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardPlayableAssetsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCardPlayableAssetsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCardPlayableAssetsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCardPlayableAssets).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
