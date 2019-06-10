/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAssetDetailComponent } from 'app/entities/m-asset/m-asset-detail.component';
import { MAsset } from 'app/shared/model/m-asset.model';

describe('Component Tests', () => {
  describe('MAsset Management Detail Component', () => {
    let comp: MAssetDetailComponent;
    let fixture: ComponentFixture<MAssetDetailComponent>;
    const route = ({ data: of({ mAsset: new MAsset(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAssetDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MAssetDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MAssetDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mAsset).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
