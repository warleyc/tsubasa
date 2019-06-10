/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAssetTagMappingDetailComponent } from 'app/entities/m-asset-tag-mapping/m-asset-tag-mapping-detail.component';
import { MAssetTagMapping } from 'app/shared/model/m-asset-tag-mapping.model';

describe('Component Tests', () => {
  describe('MAssetTagMapping Management Detail Component', () => {
    let comp: MAssetTagMappingDetailComponent;
    let fixture: ComponentFixture<MAssetTagMappingDetailComponent>;
    const route = ({ data: of({ mAssetTagMapping: new MAssetTagMapping(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAssetTagMappingDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MAssetTagMappingDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MAssetTagMappingDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mAssetTagMapping).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
